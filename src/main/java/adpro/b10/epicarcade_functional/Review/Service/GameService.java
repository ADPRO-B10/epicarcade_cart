package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Async("taskExecutorForHeavyTasks")
    public CompletableFuture<List<Game>> findAllGames() {
        return CompletableFuture.supplyAsync(() -> gameRepository.findAll());
    }

    @Async("taskExecutorForHeavyTasks")
    public CompletableFuture<Game> saveGame(Game game) {
        return CompletableFuture.supplyAsync(() -> gameRepository.save(game));
    }
}