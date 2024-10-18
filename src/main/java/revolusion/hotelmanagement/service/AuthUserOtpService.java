package revolusion.hotelmanagement.service;

import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.entity.auth.AuthUserOtp;

public interface AuthUserOtpService {

    AuthUserOtp create(AuthUserOtp authUserOtp);

    AuthUserOtp createOtp(AuthUser authUser);
}

