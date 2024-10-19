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
import revolusion.hotelmanagement.dto.HotelDTO;
import revolusion.hotelmanagement.entity.Hotel;
import revolusion.hotelmanagement.service.HotelService;

import java.util.List;

@Tag(name = "Hotel", description = "Operations about hotels")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/hotels")
@PreAuthorize("isAuthenticated()")
public class HotelController {

    private final HotelService hotelService;

    @Operation(summary = "Create a new hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel created",
                    content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "409", description = "Conflict"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotelDTO));
    }

    @Operation(summary = "Get a hotel by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel found",
                    content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "Hotel not found")})
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Integer id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @Operation(summary = "Search hotels by name or rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotels found",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "Hotels not found")})
    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> getHotelByNameOrRating(
            @Parameter(description = "City name", required = false) @RequestParam(required = false) String city,
            @Parameter(description = "Hotel name", required = false) @RequestParam(required = false) String name,
            @Parameter(description = "Hotel rating", required = false) @RequestParam(required = false, defaultValue = "1") Integer rating) {
        return ResponseEntity.ok(hotelService.getHotelByNameOrRating(name, city, rating));
    }

    @Operation(summary = "Get all hotels paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotels found",
                    content = @Content(schema = @Schema(implementation = PagedModel.class))),
            @ApiResponse(responseCode = "404", description = "Hotels not found")})
    @GetMapping
    public ResponseEntity<PagedModel<Hotel>> getPagedHotels(
            @Parameter(description = "Page number", required = false) @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "Page size", required = false) @RequestParam(defaultValue = "10") Integer size) {
        Page<Hotel> allHotels = hotelService.getPagedHotels(PageRequest.of(page, size));
        PagedModel<Hotel> hotelPagedModel = new PagedModel<>(allHotels);
        return ResponseEntity.ok(hotelPagedModel);
    }

    @Operation(summary = "Update a hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel updated",
                    content = @Content(schema = @Schema(implementation = Hotel.class))),
            @ApiResponse(responseCode = "404", description = "Hotel not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PutMapping("/update")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.updateHotel(hotel));
    }

    @Operation(summary = "Delete a hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hotel deleted"),
            @ApiResponse(responseCode = "404", description = "Hotel not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Integer id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}