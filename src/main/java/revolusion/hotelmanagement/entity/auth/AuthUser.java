package revolusion.hotelmanagement.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    private boolean active;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthRole authRole = AuthRole.USER;
}
