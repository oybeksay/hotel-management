package revolusion.hotelmanagement.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class PasswordResetToken extends Auditable {
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String email;
}
