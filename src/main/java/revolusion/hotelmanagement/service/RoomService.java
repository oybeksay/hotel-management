package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import revolusion.hotelmanagement.domein.RoomCategory;
import revolusion.hotelmanagement.dto.RoomDTO;
import revolusion.hotelmanagement.entity.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(RoomDTO roomDTO);

    Room updateRoom(Room room);

    Room getRoomById(Integer id);

    void deleteRoom(Integer id);

    Page<Room> getAllRoomsByPerNightAndCity(Pageable pageable);

    List<Room> getRoomByHotel(Integer id);

    List<Room> getAllRoomsByCategory(String city, RoomCategory roomCategory, int capacity, double pricePerNight);

    List<Room> getAllRooms();
}

