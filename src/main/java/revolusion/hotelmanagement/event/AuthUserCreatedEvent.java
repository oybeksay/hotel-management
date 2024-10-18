package revolusion.hotelmanagement.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import revolusion.hotelmanagement.entity.auth.AuthUser;

@Getter
@AllArgsConstructor
public class AuthUserCreatedEvent {
    AuthUser authUser;
}
