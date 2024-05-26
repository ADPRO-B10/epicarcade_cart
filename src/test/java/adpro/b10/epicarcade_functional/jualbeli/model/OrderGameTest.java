package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGameKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderGameTest {
    private OrderGame orderGame;
    private OrderGameKey orderGameKey;

    @BeforeEach
    public void setup() {
        orderGame = new OrderGame();
        orderGameKey = new OrderGameKey();
    }

    @Test
    public void testGettersAndSetters() {
        Order order = new Order();
        orderGame.setOrder(order);
        assertEquals(order, orderGame.getOrder());

        String gameId = "gameId";
        orderGame.setGameId(gameId);
        assertEquals(gameId, orderGame.getGameId());

        int quantity = 10;
        orderGame.setQuantity(quantity);
        assertEquals(quantity, orderGame.getQuantity());
    }

    @Test
    public void testEqualsAndHashCode() {
        OrderGameKey orderGameKey1 = new OrderGameKey();
        orderGameKey1.setOrder("order1");
        orderGameKey1.setGameId("gameId1");

        OrderGameKey orderGameKey2 = new OrderGameKey();
        orderGameKey2.setOrder("order1");
        orderGameKey2.setGameId("gameId1");

        assertEquals(orderGameKey1, orderGameKey2);
        assertEquals(orderGameKey1.hashCode(), orderGameKey2.hashCode());

        orderGameKey2.setGameId("gameId2");

        assertNotEquals(orderGameKey1, orderGameKey2);
        assertNotEquals(orderGameKey1.hashCode(), orderGameKey2.hashCode());
    }
}