package adpro.b10.epicarcade_functional.cart.model;

import adpro.b10.epicarcade_functional.model.BuyerEntity;
import jakarta.persistence.*;
import adpro.b10.epicarcade_functional.Review.Model.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Integer> games;

    @OneToOne
    private BuyerEntity buyer;

    public Cart() {
        this.games = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public Cart(ArrayList<game> games, BuyerEntity buyer) {
        this.game = game;
        this.buyer = buyer;
    }
}
