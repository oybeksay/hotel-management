package revolusion.hotelmanagement.service;

import revolusion.hotelmanagement.dto.AuthUserDTO;
import revolusion.hotelmanagement.entity.auth.AuthUser;

public interface AuthUserService {
    AuthUser save(AuthUserDTO authUserDTO);
    AuthUser findByUsername(String username);
    AuthUser update(AuthUser authUser);
    void delete(Integer id);

    String activateAccount(String code) throws InterruptedException;
}
