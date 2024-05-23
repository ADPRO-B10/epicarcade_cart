package adpro.b10.epicarcade_functional.cart.model;

import adpro.b10.epicarcade_functional.model.UserEntity;
import jakarta.persistence.*;
import adpro.b10.epicarcade_functional.Review.Model.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
public class Cart {

    @Setter
    @Getter
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
}
