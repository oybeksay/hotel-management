package revolusion.hotelmanagement.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.service.AuthUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthUserService authUserService;

    public CustomUserDetailsService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserService.findByUsername(username);
        return new CustomUserDetails(authUser);
    }
}
