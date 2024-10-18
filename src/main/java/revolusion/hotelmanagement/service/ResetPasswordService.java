package revolusion.hotelmanagement.service;

import revolusion.hotelmanagement.dto.ResetPasswordDTO;

public interface ResetPasswordService {

    String createPasswordResetToken(String email);

    String resetPassword(ResetPasswordDTO dto);

}

