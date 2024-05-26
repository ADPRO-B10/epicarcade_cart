//package adpro.b10.epicarcade_functional.cart.model;
//
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CartTest {
//
//
//
//    @Test
//    public void testAddItem() {
//        Cart shoppingCart = new Cart();
//        shoppingCart.addProduct(id123, 5);
//        shoppingCart.addProduct(id234, 50);
//        shoppingCart.addProduct(id345, 1);
//
//        assertEquals(5 + 50 + 1, shoppingCart.getTotalQuantity());
//        assertEquals(3, shoppingCart.getTotalItemTypes());
//    }
//
//    @Test
//    public void testSetItemQuantity() {
//        Cart shoppingCart = new Cart(101, 5);
//        int productId = 101; // Assuming this is a product ID
//        int initialQuantity = 5;
//        int newQuantity = 10;
//
//        shoppingCart.addProduct(productId, initialQuantity);
//        shoppingCart.setQuantity(productId, newQuantity);
//
//        assertEquals(newQuantity, shoppingCart.getProductQuantity(productId));
//    }
//
//    @Test
//    public void testRemoveItem() {
//        Cart shoppingCart = new Cart();
//        int productId = 101; // Assuming this is a product ID
//        int quantity = 5;
//
//        shoppingCart.addProduct(productId, quantity);
//        shoppingCart.removeProduct(productId);
//
//        assertEquals(0, shoppingCart.getTotalQuantity());
//        assertEquals(0, shoppingCart.getTotalItemTypes());
//    }
//
//    @Test
//    public void testUpdateItemQuantity() {
//        Cart shoppingCart = new Cart();
//        int productId = 101; // Assuming this is a product ID
//        int initialQuantity = 5;
//        int updatedQuantity = 10;
//
//        shoppingCart.addProduct(productId, initialQuantity);
//        shoppingCart.updateProductQuantity(productId, updatedQuantity);
//
//        assertEquals(updatedQuantity, shoppingCart.getProductQuantity(productId));
//    }
//
//    @Test
//    public void testTotalPrice() {
//        Cart shoppingCart = new Cart();
//        int id123 = 123; // Assuming these are the product IDs
//        int id234 = 234;
//        int id345 = 345;
//
//        double price123 = 10.0; // Assuming prices for each product
//        double price234 = 20.0;
//        double price345 = 30.0;
//
//        int quantity123 = 2;
//        int quantity234 = 3;
//        int quantity345 = 1;
//
//        shoppingCart.addProduct(id123, quantity123);
//        shoppingCart.addProduct(id234, quantity234);
//        shoppingCart.addProduct(id345, quantity345);
//
//        double expectedTotal = price123 * quantity123 + price234 * quantity234 + price345 * quantity345;
//        assertEquals(expectedTotal, shoppingCart.getTotalPrice(), 0.01); // Using delta for double comparison
//    }
//}
