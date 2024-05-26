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

}
