package adpro.b10.epicarcade_functional.jualbeli.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class OrderGameKey implements Serializable {
    private String order;
    private String gameId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderGameKey that = (OrderGameKey) o;
        return Objects.equals(order, that.order) &&
                Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, gameId);
    }
}