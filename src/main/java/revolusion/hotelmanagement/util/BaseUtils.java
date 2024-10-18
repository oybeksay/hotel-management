package revolusion.hotelmanagement.util;

import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.UUID;

@Component
public class BaseUtils {

    private final Base64.Encoder encoder = Base64.getUrlEncoder();

    public String generateOtp(Integer userId){
        return encoder.encodeToString((UUID.randomUUID().toString() + userId).getBytes());
    }
}
