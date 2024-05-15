package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.cart.model.Cart;

import java.util.List;


public interface CartService {
    public Cart addToCart(Integer productId);

    public List<Cart> getCartDetails();
}
