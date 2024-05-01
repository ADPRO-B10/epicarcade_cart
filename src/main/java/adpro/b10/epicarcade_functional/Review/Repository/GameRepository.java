package adpro.b10.epicarcade_functional.Review.Repository;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}