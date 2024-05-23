package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "payments")
@Getter
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @NotNull
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PaymentStatus status;

    public Payment(String method, Order order) {
        this.order = order;
        this.method = method;
        this.status = PaymentStatus.PENDING;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public void setOrder(Order order) {
        this.order = order;
        if (order.getPayment() != this) {
            order.setPayment(this);
        }
    }

    public BigDecimal getAmount() {
        BigDecimal amount = order.getOrderGames().stream()
            .map(orderGame -> orderGame.getGame().getPrice().multiply(BigDecimal.valueOf(orderGame.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public PaymentMethod getMethod() {
        return method;
    }
}