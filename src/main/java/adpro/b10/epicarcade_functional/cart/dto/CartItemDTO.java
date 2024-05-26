package adpro.b10.epicarcade_functional.cart.dto;

import lombok.Data;

@Data
public class CartItemDTO {
//    private Integer id;
    private String gameId;
    private Integer cartId;
    private int quantity;
    private double price;
}
