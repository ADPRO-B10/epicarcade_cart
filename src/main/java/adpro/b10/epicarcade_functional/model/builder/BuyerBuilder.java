package adpro.b10.epicarcade_functional.model.builder;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuyerBuilder extends UserBuilder {
    private String cartId;
}
