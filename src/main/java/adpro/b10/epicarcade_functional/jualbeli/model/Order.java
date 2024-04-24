package adpro.b10.epicarcade_functional.jualbeli.model;

import adpro.b10.epicarcade_functional.Review.Model.Game;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;

@Builder
@Getter
public class Order {
    String id;
    Map<Game, Integer> gamesQuantity;
    String buyerId;
    String status;

    public Order(String id, Map<Game, Integer> gamesQuantity, String buyerId) {
        this.id = id;
        this.buyerId = buyerId;
        this.status = OrderStatus.WAITING_PAYMENT.getValue();

        if (gamesQuantity.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.gamesQuantity = gamesQuantity;
        }
    }

    public Order(String id, Map<Game, Integer> gamesQuantity, String buyerId, String status) {
        this(id, gamesQuantity, buyerId);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (OrderStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}