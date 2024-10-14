package revolusion.hotelmanagement.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.*;
import revolusion.hotelmanagement.domein.AuthRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class AuthUser extends Auditable {
    @Column(unique = true, nullable = false)
    private String username;
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AuthRole authRole = AuthRole.USER;
}
