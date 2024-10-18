package revolusion.hotelmanagement.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.entity.auth.AuthUserOtp;
import revolusion.hotelmanagement.event.AuthUserCreatedEvent;
import revolusion.hotelmanagement.service.AuthUserOtpService;
import revolusion.hotelmanagement.util.MailSenderService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthUserListener {

    private final MailSenderService mailSenderService;
    private final AuthUserOtpService authUserOtpService;

    @EventListener
    public void sendActivationCodeListener(AuthUserCreatedEvent authUserCreatedEvent) {
        AuthUser authUser = authUserCreatedEvent.getAuthUser();
        AuthUserOtp otp = authUserOtpService.createOtp(authUser);

        Map<String,String> model = new HashMap<>();
        model.put("to", authUser.getEmail());
        model.put("code",otp.getCode());
        mailSenderService.sendActivationMail(model);
    }

}
