package revolusion.hotelmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HotelDTO {
    private String name;
    private String address;
    private String email;
    private Integer starRating;
    private String description;
    private String latitude;
    private String longitude;
    private Integer totalRooms;
}
