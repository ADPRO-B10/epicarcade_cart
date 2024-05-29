package adpro.b10.epicarcade_functional.history.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    BUYER("BUYER"),
    SELLER("SELLER"),
    ADMIN("ADMIN");

    private final String value;

    private UserRole(String value) {
        this.value = value;
    }

    public static boolean contains(String param){
        for (UserRole orderStatus : UserRole.values()) {
            if (orderStatus.getValue().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
