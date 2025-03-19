package pl.wsis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wsis.domain.User;
import pl.wsis.dto.UserDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select new pl.wsis.dto.UserDTO(u.id, u.fullName, u.email, u.createdAt, u.updatedAt, u.role, u.enabled) from User u")
    List<UserDTO> findAllUsers();

    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.email = :email")
    Optional<User> findByEmailFetchRole(@Param("email") String email);
}
