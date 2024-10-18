package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.ResetPasswordDTO;
import revolusion.hotelmanagement.entity.auth.PasswordResetToken;
import revolusion.hotelmanagement.repository.AuthUserRepository;
import revolusion.hotelmanagement.repository.PasswordResetTokenRepository;
import revolusion.hotelmanagement.util.MailSenderService;

import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ResetPasswordServiceImpl implements ResetPasswordService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final AuthUserRepository authUserRepository;
    private final MailSenderService mailSenderService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public String createPasswordResetToken(String email) {

        authUserRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));

        String randomNumber = String.valueOf(new Random().nextInt(100000, 999999));
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .code(randomNumber)
                .email(email).build();
        mailSenderService.sendResetPasswordMail(Map.of("to", email, "code", randomNumber));
        passwordResetTokenRepository.save(passwordResetToken);
        return "Successfully created password reset token";
    }

    @Override
    public String resetPassword(ResetPasswordDTO dto) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Password reset token not found"));
        if (dto.getCode().equals(passwordResetToken.getCode())) {
            authUserRepository.updatePasswordByEmail(passwordEncoder.encode(dto.getPassword()), dto.getEmail());
            passwordResetTokenRepository.delete(passwordResetToken);
            return "Successfully changed password!";
        }return "Invalid code";
    }
}
