package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.GuestDTO;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.mapper.GuestMapper;
import revolusion.hotelmanagement.repository.GuestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestMapper guestMapper;
    private final GuestRepository guestRepository;

    @Override
    public Guest saveGuest(GuestDTO guestDTO) {
        Guest guest = guestMapper.fromDTO(guestDTO);
        return guestRepository.save(guest);
    }

    @Override
    public Guest updateGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public Guest getGuestById(Integer id) {
        return guestRepository.findById(id).orElseThrow(() -> new RuntimeException("Guest not found"));
    }

    @Override
    public void deleteGuestById(Integer id) {
        guestRepository.deleteById(id);
    }


    @Override
    public Page<Guest> getAllGuestsPage(Pageable pageable) {
        return guestRepository.findAll(pageable);
    }
}
