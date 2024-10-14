package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import revolusion.hotelmanagement.dto.RoomDTO;
import revolusion.hotelmanagement.entity.Room;

public interface RoomService {
    Room createRoom(RoomDTO roomDTO);
    Room updateRoom(Room room);
    Room getRoomById(Integer id);
    void deleteRoom(Integer id);
    Page<Room> getAllRooms(Pageable pageable);
}
