package adpro.b10.epicarcade_functional.Review.Repository;

import adpro.b10.epicarcade_functional.Review.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

}
