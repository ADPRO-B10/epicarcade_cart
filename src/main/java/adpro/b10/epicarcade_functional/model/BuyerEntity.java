package adpro.b10.epicarcade_functional.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "buyers")
public class BuyerEntity {

    @Id
    private Integer id;
    private String username;
    private String email;
}
