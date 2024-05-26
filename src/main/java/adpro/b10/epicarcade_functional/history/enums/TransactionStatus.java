package adpro.b10.epicarcade_functional.history.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    FAILED("FAILED"),
    SUCCESS("SUCCESS"),
    CANCELLED("CANCELLED");

    private final String value;

    private TransactionStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (TransactionStatus transactionStatus : TransactionStatus.values()) {
            if (transactionStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}