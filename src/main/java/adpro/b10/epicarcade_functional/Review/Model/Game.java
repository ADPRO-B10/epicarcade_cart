package adpro.b10.epicarcade_functional.Review.Model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Table(name = "game")
@Getter
public class Game {

    @Id
    @Column(name = "idGame", nullable = false, unique = true)
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false, unique = true)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "stock", nullable = false)
    private int stock;

    public Game(){
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getStock(){return stock;}

    public String setId(String id){
        return
                this.id = id;
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
        this.name = name;
    }

    public void setPrice(int price) {
        if(price == 0){
            throw new IllegalArgumentException("Game price cannot be free");
        }
        this.price = price;
    }

    public void setStock(int stock) {
        if(stock == 0){
            throw new IllegalArgumentException("Game price cannot be free");
        }
        this.stock = stock;
    }
}
