package adpro.b10.epicarcade_functional.Review.Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class GameTest {

    @Test
    public void testGameConstructor() {
        String name = "Tetris";
        String description = "A puzzle game";
        Game game = new Game(name, description);

        assertNotNull(game.getId());
        assertEquals(name, game.getName());
        assertEquals(description, game.getDescription());
    }

    @Test
    public void testSetDescription_NullDescription_ThrowsException() {
        Game game = new Game("Pac-Man", "Arcade game");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.setDescription(null);
        });
        assertEquals("Game description cannot be empty", exception.getMessage());
    }

    @Test
    public void testSetName_NullName_ThrowsException() {
        Game game = new Game("Pac-Man", "Arcade game");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.setName(null);
        });
        assertEquals("Game name cannot be empty", exception.getMessage());
    }
}