package adpro.b10.epicarcade_functional.Review.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testGameConstructor() {
        Game game = new Game("1", "Game 1", "Description 1", 100, 10);
        assertEquals("1", game.getId());
        assertEquals("Game 1", game.getName());
        assertEquals("Description 1", game.getDescription());
        assertEquals(100, game.getPrice());
        assertEquals(10, game.getStock());
    }

    @Test
    public void testGameDefaultConstructor() {
        Game game = new Game();
        assertNull(game.getId());
        assertNull(game.getName());
        assertNull(game.getDescription());
        assertEquals(0, game.getPrice());
        assertEquals(0, game.getStock());
    }

    @Test
    public void testSetId() {
        Game game = new Game();
        game.setId("1");
        assertEquals("1", game.getId());
    }

    @Test
    public void testSetName() {
        Game game = new Game();
        game.setName("Game 1");
        assertEquals("Game 1", game.getName());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.setName(null);
        });

        String expectedMessage = "Game name cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSetDescription() {
        Game game = new Game();
        game.setDescription("Description 1");
        assertEquals("Description 1", game.getDescription());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.setDescription(null);
        });

        String expectedMessage = "Game description cannot be empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSetPrice() {
        Game game = new Game();
        game.setPrice(100);
        assertEquals(100, game.getPrice());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.setPrice(0);
        });

        String expectedMessage = "Game price cannot be free";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}
