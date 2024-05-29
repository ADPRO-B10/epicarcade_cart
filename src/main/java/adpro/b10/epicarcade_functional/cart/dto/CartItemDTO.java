package adpro.b10.epicarcade_functional.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class CartItemDTO {
    private String gameId;
    private int quantity;
}
