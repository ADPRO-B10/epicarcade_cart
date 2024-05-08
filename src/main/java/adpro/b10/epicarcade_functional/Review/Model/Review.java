package adpro.b10.epicarcade_functional.Review.Model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "review")
@Getter
public class Review {

    @Id
    @Column(name = "idReview", nullable = false, unique = true)
    private String id;

    @ManyToOne
    private Game game;

    @Column(name = "rating", nullable = false, unique = true)
    private int rating;

    @Column(name = "comment", nullable = false, unique = true)
    private String comment;

    public Review(){

    }

    public Review(String id, Game game, int rating, String comment){
        this.id = id;
        this.game = game;
        this.rating = rating;
        this.comment = comment;
    }

    public void setRating(int rating){
        if(rating == 0){
            throw new IllegalArgumentException("Rating has to be 1-5");
        }
        this.rating = rating;
    }

    public void setComment(String comment){
        if(comment == null){
            throw new IllegalArgumentException("Game comment cannot be empty");
        }
        this.comment = comment;
    }

    public void setName(Game game, String name){
        if(name == null){
            throw new IllegalArgumentException("Game name cannot be empty");
        }
        game.setName(name);
    }

}
