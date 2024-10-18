package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import revolusion.hotelmanagement.entity.auth.AuthUser;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.active = true where a.id = ?1 and a.deleted = false ")
    int updateActiveById(Integer id);

    Optional<AuthUser> findByEmail(String email);

    @Query("update AuthUser a set a.password = :password where a.id = :id")
    @Modifying
    void updatePasswordById(String password, Integer id);

    @Transactional
    @Modifying
    @Query("update AuthUser a set a.password = ?1 where a.email = ?2")
    int updatePasswordByEmail(String password, String email);
}