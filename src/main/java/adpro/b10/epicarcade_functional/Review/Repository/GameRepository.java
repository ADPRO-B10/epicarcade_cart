package adpro.b10.epicarcade_functional.Review.Repository;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

}
