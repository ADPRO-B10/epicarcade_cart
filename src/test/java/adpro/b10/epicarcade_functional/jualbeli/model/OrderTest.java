package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private Order order;
    private Map<Game, Integer> gamesQuantity;

    @BeforeEach
    public void setUp() {
        gamesQuantity = new HashMap<>();
        gamesQuantity.put(new Game(), 1);
        order = new Order("1", gamesQuantity, "buyer1");
    }

    @Test
    public void testOrderCreation() {
        assertEquals("1", order.getId());
        assertEquals(gamesQuantity, order.getGamesQuantity());
        assertEquals("buyer1", order.getBuyerId());
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), order.getStatus());
    }

    @Test
    public void testOrderCreationWithEmptyGamesQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Order("1", new HashMap<>(), "buyer1");
        });

        String expectedMessage = "Games quantity cannot be empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSetStatus() {
        order.setStatus(OrderStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    public void testSetInvalidStatus() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus("INVALID_STATUS");
        });

        String expectedMessage = "Invalid order status";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}