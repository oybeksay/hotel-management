package revolusion.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.GuestDTO;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.service.GuestService;


@RestController
@RequestMapping("/api/v1/guests")
@RequiredArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @GetMapping
    public ResponseEntity<PagedModel<Guest>> getAllGuests(@RequestParam Integer size, @RequestParam Integer page) {
        Page<Guest> guests = guestService.getAllGuests(PageRequest.of(page, size));
        PagedModel<Guest> guestPagedModel = new PagedModel<>(guests);
        return ResponseEntity.ok(guestPagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable int id) {
        return ResponseEntity.ok(guestService.getGuestById(id));
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.saveGuest(guestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable int id, @RequestBody GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(guestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable int id) {
        guestService.deleteGuestById(id);
        return ResponseEntity.noContent().build();
    }
}
