package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.HotelDTO;
import revolusion.hotelmanagement.entity.Hotel;
import revolusion.hotelmanagement.mapper.HotelMapper;
import revolusion.hotelmanagement.repository.HotelRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotelServiceImpl implements HotelService {
    private final HotelMapper hotelMapper;
    private final HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(HotelDTO hotelDTO) {
        Hotel hotel = hotelMapper.fromDTO(hotelDTO);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(Integer id) {
        return hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }

    @Override
    public void deleteHotel(Integer id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Page<Hotel> getPagedHotels(Pageable pageable) {
        return hotelRepository.findAll(pageable);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }


    @Override
    public List<Hotel> getHotelByNameOrRating(String name, String address, Integer rating) {
        return hotelRepository.findHotelsByFilter(rating, address, name);
    }
}
