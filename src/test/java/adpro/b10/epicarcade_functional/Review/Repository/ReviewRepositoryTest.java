//package adpro.b10.epicarcade_functional.Review.Repository;
//
//import adpro.b10.epicarcade_functional.Review.Model.Review;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class ReviewRepositoryTest {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Test
//    public void testSaveAndFindById() {
//        Review review = new Review();
//        review.setId("1");
//        review.setId_game("1");
//        review.setRating(5);
//        review.setComment("Great game!");
//
//        reviewRepository.save(review);
//
//        Optional<Review> foundReview = reviewRepository.findById("1");
//        assertTrue(foundReview.isPresent());
//        assertEquals(review.getComment(), foundReview.get().getComment());
//    }
//
//    @Test
//    public void testFindAll() {
//        Review review1 = new Review();
//        review1.setId("1");
//        review1.setId_game("1");
//        review1.setRating(5);
//        review1.setComment("Great game!");
//
//        Review review2 = new Review();
//        review2.setId("2");
//        review2.setId_game("2");
//        review2.setRating(4);
//        review2.setComment("Good game!");
//
//        reviewRepository.save(review1);
//        reviewRepository.save(review2);
//
//        List<Review> reviews = reviewRepository.findAll();
//        assertNotNull(reviews);
//        assertEquals(2, reviews.size());
//    }
//
//    @Test
//    public void testDeleteById() {
//        Review review = new Review();
//        review.setId("1");
//        review.setId_game("1");
//        review.setRating(5);
//        review.setComment("Great game!");
//
//        reviewRepository.save(review);
//        reviewRepository.deleteById("1");
//
//        Optional<Review> foundReview = reviewRepository.findById("1");
//        assertFalse(foundReview.isPresent());
//    }
//}
