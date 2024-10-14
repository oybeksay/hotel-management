package revolusion.hotelmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import revolusion.hotelmanagement.domein.RoomCategory;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int roomNumber;
    @Enumerated(EnumType.STRING)
    private RoomCategory roomCategory;
    private int capacity; //how many people can stay
    private double pricePerNight;  //price for one night
    private boolean available;  // whether the room is available for booking
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;
}
