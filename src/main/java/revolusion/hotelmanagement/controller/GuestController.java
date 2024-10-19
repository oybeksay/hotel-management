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
import revolusion.hotelmanagement.dto.GuestDTO;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.service.GuestService;

@RestController
@RequestMapping("/api/v1/guests")
@RequiredArgsConstructor
@Tag(name = "Guests", description = "Guests API")
@PreAuthorize("isAuthenticated()")
public class GuestController {

    private final GuestService guestService;

    @Operation(summary = "Get all guests", description = "Get all guests", tags = {"Guests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the guests",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Guest.class))}),
            @ApiResponse(responseCode = "404", description = "Guests not found", content = @Content)})
    @GetMapping
    public ResponseEntity<PagedModel<Guest>> getAllGuests(@Parameter(description = "Page size") @RequestParam(defaultValue = "10") Integer size,
                                                         @Parameter(description = "Page number") @RequestParam(defaultValue = "0") Integer page) {
        Page<Guest> guests = guestService.getAllGuestsPage(PageRequest.of(page, size));
        PagedModel<Guest> guestPagedModel = new PagedModel<>(guests);
        return ResponseEntity.ok(guestPagedModel);
    }

    @Operation(summary = "Get guest by id", description = "Get guest by id", tags = {"Guests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the guest",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Guest.class))}),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@Parameter(description = "Guest id") @PathVariable int id) {
        return ResponseEntity.ok(guestService.getGuestById(id));
    }

    @Operation(summary = "Create guest", description = "Create guest", tags = {"Guests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created the guest",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Guest.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
    @PostMapping("/create")
    public ResponseEntity<Guest> saveGuest(@Parameter(description = "Guest to create") @RequestBody GuestDTO guestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(guestService.saveGuest(guestDTO));
    }

    @Operation(summary = "Update guest", description = "Update guest", tags = {"Guests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the guest",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Guest.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)})
    @PutMapping("/update")
    public ResponseEntity<Guest> updateGuest(@Parameter(description = "Guest to update") @RequestBody Guest guest) {
        return ResponseEntity.ok(guestService.updateGuest(guest));
    }

    @Operation(summary = "Delete guest by id", description = "Delete guest by id", tags = {"Guests"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the guest"),
            @ApiResponse(responseCode = "404", description = "Guest not found", content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuestById(@Parameter(description = "Guest id") @PathVariable int id) {
        guestService.deleteGuestById(id);
        return ResponseEntity.noContent().build();
    }
}