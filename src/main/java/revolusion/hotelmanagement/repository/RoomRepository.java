package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import revolusion.hotelmanagement.domein.RoomCategory;
import revolusion.hotelmanagement.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByHotelId(Integer id);

    @Query("SELECT r FROM Room r " +
            "WHERE (r.pricePerNight <= COALESCE(?1, r.pricePerNight)) " +
            "AND (r.hotel.address = COALESCE(?2, r.hotel.address)) " +
            "AND (r.roomCategory = COALESCE(?3, r.roomCategory)) " +
            "AND (r.capacity = COALESCE(?4, r.capacity))")
    List<Room> findRooms(double pricePerNight, String city, RoomCategory roomCategory, int capacity);

}