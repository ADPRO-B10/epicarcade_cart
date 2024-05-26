package adpro.b10.epicarcade_functional.jualbeli.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;



@Entity
@Table(name = "order_game")
@IdClass(OrderGameKey.class)
@Getter
@Setter
public class OrderGame {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @Column(name = "game_id")
    @NotNull
    private String gameId;

    @Column(name = "quantity")
    @NotNull
    private int quantity;
}