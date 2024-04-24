package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.cart.dao.CartDao;
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

    public void addToCart(Integer productId) {
        //TODO: Find product by ID and add it to the cart
        //https://www.youtube.com/watch?v=c90ltL3Wkqk&ab_channel=LearnProgrammingYourself
    }
}
