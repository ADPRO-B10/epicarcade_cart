package adpro.b10.epicarcade_functional.Review.Controller;

import adpro.b10.epicarcade_functional.Review.Dto.AddReviewDTO;
import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Service.GameService;
import adpro.b10.epicarcade_functional.Review.Service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void testListGames() throws Exception {
        List<Game> games = Arrays.asList(
                new Game("1", "Game1", "Description1", 50, 10),
                new Game("2", "Game2", "Description2", 60, 20)
        );
        when(gameService.findAllGames()).thenReturn(CompletableFuture.completedFuture(games));

        mockMvc.perform(get("/reviews/games"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testListReviews() throws Exception {
        List<Review> reviews = Arrays.asList(
                new Review("1", "1", 5, "Great!"),
                new Review("2", "1", 4, "Good")
        );
        when(reviewService.findAllReviews()).thenReturn(CompletableFuture.completedFuture(reviews));

        mockMvc.perform(get("/reviews/list-reviews"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testAddReview() throws Exception {
        AddReviewDTO reviewDTO = new AddReviewDTO();
        reviewDTO.setId_game("1");
        reviewDTO.setRating(5);
        reviewDTO.setComment("Awesome game");

        Review review = new Review("1", "1", 5, "Awesome game");
        when(reviewService.addReview(any(String.class), any(Integer.class), any(String.class)))
                .thenReturn(CompletableFuture.completedFuture(review));

        mockMvc.perform(post("/reviews/add-review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reviewDTO)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteReview() throws Exception {
        when(reviewService.deleteReview("1")).thenReturn(CompletableFuture.completedFuture(null));

        mockMvc.perform(delete("/reviews/delete-review/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
