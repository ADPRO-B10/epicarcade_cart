package adpro.b10.epicarcade_functional.repository;

import adpro.b10.epicarcade_functional.model.AdminEntity;
import adpro.b10.epicarcade_functional.model.BuyerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByUsername(String username);
    Optional<AdminEntity> findByEmail(String email);
    Optional<AdminEntity> findById(Integer userId);
}
