package adpro.b10.epicarcade_functional.jualbeli.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;

@Builder
@Getter
public class Payment {
    String id;
    String method;
    String orderId;
    String status;

    public Payment(String id, String method, String orderId) {
        this.id = id;
        this.orderId = orderId;
        this.method = method;
        this.status = "PENDING";
    }

    public Payment(String id, String method, String orderId, String status) {
        this(id, method, orderId);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}