package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import revolusion.hotelmanagement.dto.GuestDTO;
import revolusion.hotelmanagement.entity.Guest;

import java.util.List;

public interface GuestService {
    Guest saveGuest(GuestDTO guestDTO);

    Guest updateGuest(Guest guest);

    Guest getGuestById(Integer id);

    void deleteGuestById(Integer id);

    Page<Guest> getAllGuestsPage(Pageable pageable);

}
