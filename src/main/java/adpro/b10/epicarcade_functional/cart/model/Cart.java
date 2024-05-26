package adpro.b10.epicarcade_functional.cart.model;

import adpro.b10.epicarcade_functional.model.BuyerEntity;
import jakarta.persistence.*;
import adpro.b10.epicarcade_functional.Review.Model.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
