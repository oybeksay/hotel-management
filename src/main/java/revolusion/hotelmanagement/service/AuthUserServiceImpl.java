package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.AuthUserDTO;
import revolusion.hotelmanagement.entity.auth.AuthUser;
import revolusion.hotelmanagement.mapper.AuthUserMapper;
import revolusion.hotelmanagement.repository.AuthUserRepository;

@Service
@RequiredArgsConstructor
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserMapper authUserMapper;
    private final AuthUserRepository authUserRepository;

    @Override
    public AuthUser save(AuthUserDTO authUserDTO) {
        AuthUser authUser = authUserMapper.fromDTO(authUserDTO);
        return authUserRepository.save(authUser);
    }

    @Override
    public AuthUser findByUsername(String username) {
        return authUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public AuthUser update(AuthUser authUser) {
        return null;
    }

    @Override
    public void delete(AuthUser authUser) {
        authUserRepository.delete(authUser);
    }
}
