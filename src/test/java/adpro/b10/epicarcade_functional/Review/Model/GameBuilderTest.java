package adpro.b10.epicarcade_functional.Review.Model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GameBuilderTest {

    @Test
    public void testBuildGameWithAllFields() {
        String id = UUID.randomUUID().toString();
        String name = "Test Game";
        String description = "This is a test game.";
        int price = 50;
        int stock = 100;

        Game game = new GameBuilder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build();

        assertNotNull(game);
        assertEquals(id, game.getId());
        assertEquals(name, game.getName());
        assertEquals(description, game.getDescription());
        assertEquals(price, game.getPrice());
        assertEquals(stock, game.getStock());
    }

    @Test
    public void testBuildGameWithGeneratedId() {
        String name = "Test Game";
        String description = "This is a test game.";
        int price = 50;
        int stock = 100;

        Game game = new GameBuilder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build();

        assertNotNull(game);
        assertNotNull(game.getId());
        assertEquals(name, game.getName());
        assertEquals(description, game.getDescription());
        assertEquals(price, game.getPrice());
        assertEquals(stock, game.getStock());
    }

    @Test
    public void testBuildGameWithDefaultValues() {
        Game game = new GameBuilder().build();

        assertNotNull(game);
        assertNotNull(game.getId());
        assertNull(game.getName());
        assertNull(game.getDescription());
        assertEquals(0, game.getPrice());
        assertEquals(0, game.getStock());
    }
}