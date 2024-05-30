package adpro.b10.epicarcade_functional.cart.enums;

import lombok.Getter;

@Getter
public enum CartStatus {
    EMPTY("EMPTY"),
    FILLED("FILLED");

    private final String value;

    CartStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
            for (CartStatus cartStatus : CartStatus.values()) {
                if (cartStatus.name().equals(param)) {return true;}
            }
            return false;
        }
}
