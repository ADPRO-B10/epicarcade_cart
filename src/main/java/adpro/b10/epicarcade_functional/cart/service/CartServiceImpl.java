package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.Review.Repository.GameRepository;
import adpro.b10.epicarcade_functional.cart.enums.CartStatus;
import adpro.b10.epicarcade_functional.cart.model.CartItem;
import adpro.b10.epicarcade_functional.cart.repository.CartRepository;
import adpro.b10.epicarcade_functional.cart.model.Cart;
import adpro.b10.epicarcade_functional.Review.Model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private final CartRepository shoppingCartRepository;
    @Autowired
    private final GameRepository gameRepository;

    public CartServiceImpl(CartRepository shoppingCartRepository, GameRepository gameRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Cart addToCart(String email, String itemId, Integer quantity) {
        Game game = gameRepository.findById(itemId).orElse(null);

        if (game == null || game.getStock() <= 0) {
            throw new IllegalArgumentException("The game is out of stock or does not exist.");
        }

        Cart cart = shoppingCartRepository.findByUserEmail(email);

        if (cart == null) {
            cart = new Cart();
            cart.setUserEmail(email);
        }

        // Initialize items list if null
        if (cart.getItems() == null) {
            cart.setItems(new ArrayList<>());
            cart.setCurrentStatus(CartStatus.EMPTY.getValue());
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
            newItem.setProductPrice(game.getPrice());
            newItem.setQuantity(quantity);
            newItem.setUserEmail(email);
            newItem.setCart(cart);
            items.add(newItem);
        }

        cart.setItems(items);
        cart.setCurrentStatus(CartStatus.FILLED.getValue());
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
                String productId = item.getProductId();
                Integer quantity = item.getQuantity();

                // Check if the game is available (stock > 0) before adding it to cart details
                Game game = gameRepository.findById(productId).orElse(null);
                if (game != null && game.getStock() > 0) {
                    cartDetails.put(productId, quantity);
                }
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
