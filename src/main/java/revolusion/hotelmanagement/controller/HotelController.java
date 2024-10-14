package revolusion.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.HotelDTO;
import revolusion.hotelmanagement.entity.Hotel;
import revolusion.hotelmanagement.service.HotelService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/hotels")
public class HotelController {
    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(hotelDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Integer id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @GetMapping
    public ResponseEntity<PagedModel<Hotel>> getAllHotels(@RequestParam(defaultValue = "0") Integer page,
                                                          @RequestParam Integer size) {
        Page<Hotel> allHotels = hotelService.getAllHotels(PageRequest.of(page, size));
        PagedModel<Hotel> hotelPagedModel = new PagedModel<>(allHotels);
        return ResponseEntity.ok(hotelPagedModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.updateHotel(hotel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Integer id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}


