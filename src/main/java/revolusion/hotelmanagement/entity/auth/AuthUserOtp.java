package revolusion.hotelmanagement.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class AuthUserOtp extends Auditable {
    @Column(nullable = false)
    private String code;
    @Column(nullable = false)
    private Integer userId;
    @Future
    @Column(nullable = false)
    private LocalDateTime expiryDate;
}
