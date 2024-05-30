package adpro.b10.epicarcade_functional.Review.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddReviewDTO {
    private String id_game;
    private int rating;
    private String comment;
}