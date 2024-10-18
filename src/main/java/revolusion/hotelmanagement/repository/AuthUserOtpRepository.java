package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import revolusion.hotelmanagement.entity.auth.AuthUserOtp;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AuthUserOtpRepository extends JpaRepository<AuthUserOtp, Integer> {
    @Query("select a from AuthUserOtp a where upper(a.code) = upper(?1) and a.deleted = false")
    Optional<AuthUserOtp> findByCode(String code);

    @Transactional
    @Modifying
    @Query("update AuthUserOtp a set a.code = ?1, a.expiryDate = ?2 where a.userId = ?3")
    int updateCodeAndExpiryDateByUserId(String code, LocalDateTime expiryDate, Integer userId);
}