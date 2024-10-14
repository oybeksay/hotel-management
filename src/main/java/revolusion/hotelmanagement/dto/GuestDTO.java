package revolusion.hotelmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GuestDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private boolean verified;
}
