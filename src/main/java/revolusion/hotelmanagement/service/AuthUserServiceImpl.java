package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.AuthUserDTO;
import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.entity.auth.AuthUserOtp;
import revolusion.hotelmanagement.event.AuthUserCreatedEvent;
import revolusion.hotelmanagement.mapper.AuthUserMapper;
import revolusion.hotelmanagement.repository.AuthUserOtpRepository;
import revolusion.hotelmanagement.repository.AuthUserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher publisher;
    private final AuthUserOtpRepository authUserOtpRepository;


    @Override
    public AuthUser save(AuthUserDTO authUserDTO) {
        AuthUser authUser = authUserMapper.fromDTO(authUserDTO);
        authUser.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));
        AuthUser save = authUserRepository.save(authUser);
        publisher.publishEvent(new AuthUserCreatedEvent(save));
        return save;
    }

    @Override
    public AuthUser findByUsername(String username) {
        return authUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public AuthUser update(AuthUser authUser) {
        return authUserRepository.save(authUser);
    }

    @Override
    public void delete(Integer id) {
        authUserRepository.deleteById(id);
    }

    @Override
    public String activateAccount(String code){
        AuthUserOtp authUserOtp = authUserOtpRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Invalid code"));
        if (authUserOtp.getExpiryDate().isBefore(LocalDateTime.now())) {
            authUserOtpRepository.delete(authUserOtp);
            publisher.publishEvent(new AuthUserCreatedEvent(authUserRepository.findById(authUserOtp.getUserId()).get()));
            return "Your code is expired,new code sent to your email";
        }
        authUserRepository.updateActiveById(authUserOtp.getUserId());
        return "Account successfully activated";
    }





}
