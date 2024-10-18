package revolusion.hotelmanagement.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.entity.auth.AuthUserOtp;

@Getter
@AllArgsConstructor
public class AuthUserCreatedEvent {
    AuthUser authUser;
}
