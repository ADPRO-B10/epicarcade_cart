package adpro.b10.epicarcade_functional.Review.Model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "Game")
@Getter
public class Game {

    @Id
    @Column(name = "idGame", nullable = false, unique = true)
    private String id;


    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    public Game(String name, String description){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public void setDescription(String description) {
        if(description == null){
            throw new IllegalArgumentException("Game description cannot be empty");
        }
        this.description = description;
    }
    public void setName(String name) {
        if(name == null){
            throw new IllegalArgumentException("Game name cannot be empty");
        }
        this.description = description;
    }
}