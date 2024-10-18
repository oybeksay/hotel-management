package revolusion.hotelmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.RoomDTO;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.entity.Room;
import revolusion.hotelmanagement.repository.GuestRepository;
import revolusion.hotelmanagement.service.GuestService;
import revolusion.hotelmanagement.service.OrderService;
import revolusion.hotelmanagement.service.RoomService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("isAuthenticated()")
@Tag(name = "Admin", description = "Admin API")
public class AdminController {


    private final GuestRepository guestRepository;
    private final GuestService guestService;
    private final RoomService roomService;
    private final OrderService orderService;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the users",
                    content = @Content(schema = @Schema(implementation = Guest.class))),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content)})
    @GetMapping("/guests")
    public ResponseEntity<List<Guest>> getUsers() {
        return ResponseEntity.ok(guestRepository.findAll());
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the user",
                    content = @Content(schema = @Schema(implementation = Guest.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @PutMapping("/guests")
    public ResponseEntity<Guest> updateUser(@RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.updateGuest(guest));
    }

    @Operation(summary = "Get all rooms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the rooms",
                    content = @Content(schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "404", description = "Rooms not found",
                    content = @Content)})
    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @Operation(summary = "Add a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the room",
                    content = @Content(schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)})
    @PostMapping("/rooms")
    public ResponseEntity<Room> addRoom(@RequestBody RoomDTO roomDTO) {
        return new ResponseEntity<>(roomService.createRoom(roomDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the room",
                    content = @Content(schema = @Schema(implementation = Room.class))),
            @ApiResponse(responseCode = "404", description = "Room not found",
                    content = @Content)})
    @PutMapping("/rooms")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(room));
    }

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the orders",
                    content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Orders not found",
                    content = @Content)})
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

}