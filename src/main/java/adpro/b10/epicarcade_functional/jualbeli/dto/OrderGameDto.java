package adpro.b10.epicarcade_functional.jualbeli.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderGameDto {
    private String orderId;
    private String gameId;
    private int quantity;
}