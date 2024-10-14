package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import revolusion.hotelmanagement.dto.HotelDTO;
import revolusion.hotelmanagement.entity.Hotel;

public interface HotelService {
    Hotel createHotel(HotelDTO hotelDTO);
    Hotel updateHotel(Hotel hotel);
    Hotel getHotelById(Integer id);
    void deleteHotel(Integer id);
    Page<Hotel> getAllHotels(Pageable pageable);
}
