package adpro.b10.epicarcade_functional.Review.Repository;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;

    @Test
    public void testSaveAndFindById() {
        Game game = new Game();
        game.setId("1");
        game.setName("Test Game");
        game.setDescription("Test Description");
        game.setPrice(50);
        game.setStock(100);

        gameRepository.save(game);

        Optional<Game> foundGame = gameRepository.findById("1");
        assertTrue(foundGame.isPresent());
        assertEquals(game.getName(), foundGame.get().getName());
    }

    @Test
    public void testFindAll() {
        Game game1 = new Game();
        game1.setId("1");
        game1.setName("Test Game 1");
        game1.setDescription("Test Description 1");
        game1.setPrice(50);
        game1.setStock(100);

        Game game2 = new Game();
        game2.setId("2");
        game2.setName("Test Game 2");
        game2.setDescription("Test Description 2");
        game2.setPrice(60);
        game2.setStock(200);

        gameRepository.save(game1);
        gameRepository.save(game2);

        Iterable<Game> games = gameRepository.findAll();
        assertNotNull(games);
        assertEquals(2, ((Collection<?>) games).size());
    }

    @Test
    public void testDeleteById() {
        Game game = new Game();
        game.setId("1");
        game.setName("Test Game");
        game.setDescription("Test Description");
        game.setPrice(50);
        game.setStock(100);

        gameRepository.save(game);
        gameRepository.deleteById("1");

        Optional<Game> foundGame = gameRepository.findById("1");
        assertFalse(foundGame.isPresent());
    }
}
