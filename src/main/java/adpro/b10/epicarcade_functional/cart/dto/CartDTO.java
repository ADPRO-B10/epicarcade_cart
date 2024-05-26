package adpro.b10.epicarcade_functional.cart.dto;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Data
public class CartDTO {
    private String email;
    private Map<String, Integer> items;
    private double totalPrice;
}
