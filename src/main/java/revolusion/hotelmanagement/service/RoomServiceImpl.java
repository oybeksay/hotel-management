package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.RoomDTO;
import revolusion.hotelmanagement.entity.Hotel;
import revolusion.hotelmanagement.entity.Room;
import revolusion.hotelmanagement.mapper.RoomMapper;
import revolusion.hotelmanagement.repository.HotelRepository;
import revolusion.hotelmanagement.repository.RoomRepository;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomMapper roomMapper;
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @Override
    public Room createRoom(RoomDTO roomDTO) {
        Hotel hotel = hotelRepository.findById(roomDTO.getHotelId()).orElseThrow(() -> new RuntimeException("Hotel not found"));
        Room room = roomMapper.fromDTO(roomDTO);
        room.setHotel(hotel);
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room getRoomById(Integer id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Page<Room> getAllRooms(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }
}
