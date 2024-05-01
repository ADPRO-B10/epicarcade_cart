package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.cart.dao.CartDao;
import adpro.b10.epicarcade_functional.cart.dao.ProductDao;
import adpro.b10.epicarcade_functional.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public Cart addToCart(Integer productId) {
        Game game = productDao.findById(productId).get();

        String username = JwtRequestFilter.CURRENT_USER;

        User user = null;
        if(username != null){
            User user = userDao.findById(username).get();
        }

        if (game != null && user != null) {
            Cart cart = new Cart(game, user);
            return cartDao.save(cart);
        }

        return null;
    }
}
