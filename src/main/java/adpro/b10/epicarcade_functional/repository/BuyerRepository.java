package adpro.b10.epicarcade_functional.repository;

import adpro.b10.epicarcade_functional.model.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<BuyerEntity, Integer> {
    Optional<BuyerEntity> findByUsername(String username);
    Optional<BuyerEntity> findByEmail(String email);
    Optional<BuyerEntity> findById(Integer userId);
}
