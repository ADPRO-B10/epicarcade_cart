package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.cart.dao.CartDao;
import adpro.b10.epicarcade_functional.cart.dao.ProductDao;
import adpro.b10.epicarcade_functional.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface CartService {

    public Cart addToCart(Integer productId);
}
