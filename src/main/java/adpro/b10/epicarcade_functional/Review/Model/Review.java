package adpro.b10.epicarcade_functional.Review.Model;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "review")
@Getter
public class Review {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "idReview", updatable = false, nullable = false, unique = true)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_game", nullable = false)
    private Game game;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "comment", nullable = false)
    private String comment;

    public Review() {}

    public Review(String id, Game game, int rating, String comment) {
        this.id = id;
        this.game = game;
        this.rating = rating;
        this.comment = comment;
    }

    public void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating has to be between 1 and 5");
        }
        this.rating = rating;
    }

    public void setComment(String comment) {
        if (comment == null || comment.trim().isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }
        this.comment = comment;
    }
}