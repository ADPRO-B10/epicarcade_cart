package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }
}
