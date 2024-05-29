package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Repository.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllGames() throws Exception {
        Game game1 = new Game();
        game1.setId("1");
        game1.setName("Game1");

        Game game2 = new Game();
        game2.setId("2");
        game2.setName("Game2");

        List<Game> games = Arrays.asList(game1, game2);

        when(gameRepository.findAll()).thenReturn(games);

        CompletableFuture<List<Game>> future = gameService.findAllGames();
        List<Game> result = future.get();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Game1", result.get(0).getName());
        assertEquals("Game2", result.get(1).getName());

        verify(gameRepository, times(1)).findAll();
    }

    @Test
    public void testSaveGame() throws Exception {
        Game game = new Game();
        game.setId("1");
        game.setName("Game1");

        when(gameRepository.save(any(Game.class))).thenReturn(game);

        CompletableFuture<Game> future = gameService.saveGame(game);
        Game result = future.get();

        assertNotNull(result);
        assertEquals("Game1", result.getName());

        verify(gameRepository, times(1)).save(any(Game.class));
    }
}
