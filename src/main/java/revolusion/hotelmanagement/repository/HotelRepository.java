package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import revolusion.hotelmanagement.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}