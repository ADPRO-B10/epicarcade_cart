package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class GameService {
    public List<Game> getAllGames() {
        return Arrays.asList(
            new Game("1", "Game One", "First game description", 20, 5),
            new Game("2", "Game Two", "Second game description", 30, 3)
        );
    }
}
