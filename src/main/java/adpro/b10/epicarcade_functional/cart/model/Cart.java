package adpro.b10.epicarcade_functional.cart.model;

import adpro.b10.epicarcade_functional.model.UserEntity;
import jakarta.persistence.*;
import adpro.b10.epicarcade_functional.Review.Model.Game;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cartId;
    @OneToOne
    private Game game;
    @OneToOne
    private UserEntity user;

    public Cart(Game game, UserEntity user) {
        this.game = game;
        this.user = user;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}
