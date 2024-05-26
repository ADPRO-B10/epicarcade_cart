package adpro.b10.epicarcade_functional.history.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long buyerId;

    @Column(nullable = false)
    private Long sellerId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date transactionDate;

    public TransactionItem() {
    }

    public TransactionItem(Long buyerId, Long sellerId, Long productId, double amount, String status, Date transactionDate) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.amount = amount;
        this.status = status;
        this.transactionDate = transactionDate;
    }
}
