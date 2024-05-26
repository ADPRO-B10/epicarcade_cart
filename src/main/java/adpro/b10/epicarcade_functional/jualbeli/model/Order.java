package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;

    @ManyToMany
    @JoinTable(
        name = "order_game", 
        joinColumns = @JoinColumn(name = "order_id"), 
        inverseJoinColumns = @JoinColumn(name = "game_id"))
    @MapKeyColumn(name = "quantity")
    private Map<Game, Integer> gamesQuantity;

    @Column(name = "buyer_id")
    private String buyerId;

    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    public void setStatus(String status) {
        if (OrderStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        if (payment.getOrder() != this) {
            payment.setOrder(this);
        }
    }
}