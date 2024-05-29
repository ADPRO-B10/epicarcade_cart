package adpro.b10.epicarcade_functional.cart.model;

import adpro.b10.epicarcade_functional.cart.model.Cart;
import adpro.b10.epicarcade_functional.cart.model.CartItem;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class CartTest {

    @Test
    public void testCartInitialization() {
        Cart cart = new Cart();

        String userEmail = "test@example.com";
        String currentStatus = "Active";
        List<CartItem> items = new ArrayList<>();

        cart.setUserEmail(userEmail);
        cart.setCurrentStatus(currentStatus);
        cart.setItems(items);

        assertEquals(userEmail, cart.getUserEmail());
        assertEquals(currentStatus, cart.getCurrentStatus());
        assertEquals(items, cart.getItems());
    }

    @Test
    public void testCartInitializationWithNoArgsConstructor() {
        Cart cart = new Cart();

        assertNull(cart.getUserEmail());
        assertNull(cart.getCurrentStatus());
        assertNull(cart.getItems());
    }
}
