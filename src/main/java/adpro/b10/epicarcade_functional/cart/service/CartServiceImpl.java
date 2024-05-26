package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.cart.model.CartItem;
import adpro.b10.epicarcade_functional.cart.repository.CartRepository;
import adpro.b10.epicarcade_functional.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository shoppingCartRepository;

    @Override
    public Cart addToCart(String email, String itemId, Integer quantity) {
        Cart cart = shoppingCartRepository.findByUserEmail(email);

        if (cart == null) {
            cart = new Cart();
            cart.setUserEmail(email);
        }

        List<CartItem> items = cart.getItems();
        boolean itemFound = false;

        //Check if already added to cart
        for (CartItem item : items) {
            if (item.getProductId().equals(itemId)) {
                item.setQuantity(item.getQuantity() + quantity);
                itemFound = true;
                break;
            }
        }

        //Item not found
        if (!itemFound) {
            CartItem newItem = new CartItem();
            newItem.setProductId(itemId);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            items.add(newItem);
        }

        cart.setItems(items);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public void removeFromCart(String email, String itemId) {
        Cart cart = shoppingCartRepository.findByUserEmail(email);

        if (cart != null) {
            List<CartItem> items = cart.getItems();
            items.removeIf(item -> item.getProductId().equals(itemId));
            cart.setItems(items);
            shoppingCartRepository.save(cart);
        }
    }

//    @Override
//    public void checkout(String username) {
//        // Perform checkout operation
//    }

    @Override
    public Map<String, Integer> getCartDetails(String userEmail) {
        Cart cart = shoppingCartRepository.findByUserEmail(userEmail);
        Map<String, Integer> cartDetails = new HashMap<>();

        if (cart != null) {
            List<CartItem> cartItems = cart.getItems();
            for (CartItem item : cartItems) {
                cartDetails.put(item.getProductId(), item.getQuantity());
            }
        }

        return cartDetails;
    }


    @Override
    public double getTotalPrice(Cart cart) {
        double totalPrice = 0;
        for (CartItem item : cart.getItems()) {
            totalPrice += item.getProductPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public Cart getCartByUserEmail(String userEmail) {
        return shoppingCartRepository.findByUserEmail(userEmail);
    }
}
