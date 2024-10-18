package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.entity.auth.AuthUserOtp;
import revolusion.hotelmanagement.repository.AuthUserOtpRepository;
import revolusion.hotelmanagement.util.BaseUtils;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthUserOtpServiceImpl implements AuthUserOtpService {
    private final AuthUserOtpRepository authUserOtpRepository;
    private final BaseUtils baseUtils;


    @Override
    public AuthUserOtp create(AuthUserOtp authUserOtp) {
        return authUserOtpRepository.save(authUserOtp);
    }

    @Override
    public AuthUserOtp createOtp(AuthUser authUser) {
        AuthUserOtp authUserOtp = AuthUserOtp.builder()
                .code(baseUtils.generateOtp(authUser.getId()))
                .userId(authUser.getId())
                .expiryDate(LocalDateTime.now().plusMinutes(10)).build();
        return create(authUserOtp);
    }

}
