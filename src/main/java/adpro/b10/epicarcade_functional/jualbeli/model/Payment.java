package adpro.b10.epicarcade_functional.jualbeli.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "method")
    private String method;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "status")
    private String status;

    public Payment(String id, String method, Order order) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.status = PaymentStatus.PENDING.getValue();
    }

    public Payment(String id, String method, Order order, String status) {
        this(id, method, order);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setOrder(Order order) {
        this.order = order;
        if (order.getPayment() != this) {
            order.setPayment(this);
        }
    }

    public double getAmount() {
        return order.getGamesQuantity().entrySet().stream()
            .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }
}