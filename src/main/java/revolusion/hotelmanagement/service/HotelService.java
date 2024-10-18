package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import revolusion.hotelmanagement.dto.HotelDTO;
import revolusion.hotelmanagement.entity.Hotel;

import java.util.List;

public interface HotelService {
    Hotel createHotel(HotelDTO hotelDTO);
    Hotel updateHotel(Hotel hotel);
    Hotel getHotelById(Integer id);
    void deleteHotel(Integer id);
    Page<Hotel> getPagedHotels(Pageable pageable);
    List<Hotel> getHotelByNameOrRating(String name, String address, Integer rating);
}
