package adpro.b10.epicarcade_functional.cart.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Entity
@NoArgsConstructor
@Table (name = "shopping_carts")
public class Cart {

    @Id
    private String userEmail;

    private String currentStatus;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items; // List to hold cart items
}
