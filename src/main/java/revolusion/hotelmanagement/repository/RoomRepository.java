package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import revolusion.hotelmanagement.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}