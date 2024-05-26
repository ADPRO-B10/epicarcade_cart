package adpro.b10.epicarcade_functional.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "sellers")
public class SellerEntity {

    @Id
    private Integer id;
    private String username;
    private String email;
}
