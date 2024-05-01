package adpro.b10.epicarcade_functional.cart.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    @Test
    public void testAddItem() {
        Cart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(id123, 5);
        shoppingCart.addProduct(id234, 50);
        shoppingCart.addProduct(id345, 1);

        assertEquals(5 + 50 + 1, shoppingCart.getTotalQuantity());
        assertEquals(3, shoppingCart.getTotalItemTypes());
    }
}
