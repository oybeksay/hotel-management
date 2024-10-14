package revolusion.hotelmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import revolusion.hotelmanagement.domein.RoomCategory;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RoomDTO {
    private int roomNumber;
    private RoomCategory roomCategory;
    private int capacity;
    private double pricePerNight;
    private boolean available;
    private int hotelId;  //foreign key
}
