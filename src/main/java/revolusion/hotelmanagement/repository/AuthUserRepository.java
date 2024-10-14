package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import revolusion.hotelmanagement.entity.auth.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsername(String username);
}