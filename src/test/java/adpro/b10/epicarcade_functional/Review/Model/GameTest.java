package adpro.b10.epicarcade_functional.Review.Model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testGameConstructor() {
        String name = "Tetris";
        String description = "A puzzle game";
        String id1 = "123456789";
        int price = 20000;
        int stock = 5;
        Game game = new GameBuilder().name(name).description(description).id(id1)
                .price(price).stock(stock).build();

        assertNotNull(game.getId(), "ID should not be null");
        assertNotNull(game.getName(), "Game name cannot be empty");
        assertEquals(id1, game.getId(), "ID should match the input");
        assertEquals(stock, game.getStock(), "Stock should match the input");
        assertEquals(name, game.getName(), "Name should match the input");
        assertEquals(description, game.getDescription(), "Description should match the input");

    }

    @Test
    public void testSetDescription_NullDescription_ThrowsException() {
        Game game = new GameBuilder().name("Pac-Man").description("Arcade game").id("123456789")
                .price(20000).stock(5).build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.setDescription(null);
        });

        assertEquals("Game description cannot be empty", exception.getMessage());
    }

    @Test
    public void testSetName_NullName_ThrowsException() {
        Game game = new GameBuilder().name("Pac-Man").description("Arcade game").id("123456789")
                .price(20000).stock(5).build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.setName(null);
        });

        assertEquals("Game name cannot be empty", exception.getMessage());
    }
}
