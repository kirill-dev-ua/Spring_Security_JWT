package pl.wsis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsis.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);

}