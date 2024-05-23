package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import main.java.adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    private String id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderGame> orderGames;

    @NotNull
    @Column(name = "buyer_id")
    private String buyerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    private Payment payment;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public void addOrderGame(OrderGame orderGame) {
        this.orderGames.add(orderGame);
        orderGame.setOrder(this);
    }
}