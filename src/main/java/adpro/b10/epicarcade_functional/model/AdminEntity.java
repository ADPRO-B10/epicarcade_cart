package adpro.b10.epicarcade_functional.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "admins")
public class AdminEntity {

    @Id
    private Integer id;
    private String username;
    private String email;
}
