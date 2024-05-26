package adpro.b10.epicarcade_functional.Review.Repository;

import adpro.b10.epicarcade_functional.Review.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}