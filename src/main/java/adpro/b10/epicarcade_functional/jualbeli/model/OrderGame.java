package main.java.adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_game")
@Getter
public class OrderGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @NotNull
    private Game game;

    @Column(name = "quantity")
    @NotNull
    private int quantity;

    public void setOrder(Order order) {
        this.order = order;
        if (!order.getOrderGames().contains(this)) {
            order.getOrderGames().add(this);
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}