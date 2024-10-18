package revolusion.hotelmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.domein.RoomCategory;
import revolusion.hotelmanagement.dto.RoomDTO;
import revolusion.hotelmanagement.entity.Room;
import revolusion.hotelmanagement.service.RoomService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rooms")
@Tag(name = "Rooms", description = "End points for managing rooms")
@PreAuthorize("isAuthenticated()")
public class RoomController {

    private final RoomService roomService;

    @Operation(summary = "Get all rooms", description = "Get all rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the rooms",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagedModel.class)) }),
            @ApiResponse(responseCode = "404", description = "Rooms not found",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<PagedModel<Room>> getAllRooms(
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer size,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") Integer page) {
        Page<Room> rooms = roomService.getAllRoomsByPerNightAndCity(PageRequest.of(page, size));
        PagedModel<Room> roomPagedModel = new PagedModel<>(rooms);
        return ResponseEntity.ok(roomPagedModel);
    }

    @Operation(summary = "Get room by id", description = "Get room by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the room",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Room.class)) }),
            @ApiResponse(responseCode = "404", description = "Room not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Integer id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @Operation(summary = "Get rooms by hotel", description = "Get rooms by hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the rooms",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "404", description = "Rooms not found",
                    content = @Content) })
    @GetMapping("/hotel/{id}")
    public ResponseEntity<List<Room>> getRoomByHotel(@PathVariable Integer id) {
        return ResponseEntity.ok(roomService.getRoomByHotel(id));
    }

    @Operation(summary = "Get rooms by filter", description = "Get rooms by filter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the rooms",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "404", description = "Rooms not found",
                    content = @Content) })
    @GetMapping("/filter")
    public ResponseEntity<List<Room>> getAllRoomsByPerNightAndCity(
            @Parameter(description = "Price per night") @RequestParam(required = false, defaultValue = "1") double pricePerNight,
            @Parameter(description = "City") @RequestParam(required = false) String city,
            @Parameter(description = "Room category") @RequestParam(required = false) RoomCategory roomCategory,
            @Parameter(description = "Capacity") @RequestParam(required = false, defaultValue = "1") int capacity) {
        return ResponseEntity.ok(roomService.getAllRoomsByCategory(city, roomCategory, capacity, pricePerNight));
    }

    @Operation(summary = "Create room", description = "Create room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Room created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Room.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.createRoom(roomDTO));
    }

    @Operation(summary = "Update room", description = "Update room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Room.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content) })
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(room));
    }

    @Operation(summary = "Delete room", description = "Delete room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Room deleted",
                    content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}