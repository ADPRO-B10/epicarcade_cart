package adpro.b10.epicarcade_functional.jualbeli.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    GOPAY("GOPAY"),
    OVO("OVO");

    private final String value;

    private PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.getValue().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
