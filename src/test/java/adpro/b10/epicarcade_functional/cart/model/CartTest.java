package adpro.b10.epicarcade_functional.cart.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    public void testAddItem() {
        Cart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(id123, 5);
        shoppingCart.addItem(id234, 50);
        shoppingCart.addItem(id345, 1;

        assertEquals(5 + 50 + 1, shoppingCart.getTotalQuantity());
        assertEquals(3, shoppingCart.getTotalItemTypes());
    }
}
