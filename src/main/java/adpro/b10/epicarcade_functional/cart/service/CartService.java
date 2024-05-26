package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.cart.dto.CartItemDTO;
import adpro.b10.epicarcade_functional.cart.model.Cart;

import java.util.List;
import java.util.Map;


public interface CartService {
    public Cart addToCart(String email, String itemId, Integer quantity);

    public void removeFromCart(String email, String itemId);

//    public Cart incrementProductQuantity(String username, Integer productId);
//
//    public Cart decrementProductQuantity(String username, Integer productId);
//
//    public void checkout(String username);

    public Map<String, Integer> getCartDetails(String username);

    double getTotalPrice(Cart cart);

    Cart getCartByUserEmail(String userEmail);
}
