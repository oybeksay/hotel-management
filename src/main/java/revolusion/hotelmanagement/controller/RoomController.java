package revolusion.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.RoomDTO;
import revolusion.hotelmanagement.entity.Room;
import revolusion.hotelmanagement.service.RoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<PagedModel<Room>> getAllRooms(@RequestParam Integer size, @RequestParam Integer page) {
        Page<Room> rooms = roomService.getAllRooms(PageRequest.of(page, size));
        PagedModel<Room> roomPagedModel = new PagedModel<>(rooms);
        return ResponseEntity.ok(roomPagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Integer id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomService.createRoom(roomDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(room));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}

