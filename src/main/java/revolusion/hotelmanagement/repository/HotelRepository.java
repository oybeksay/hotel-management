package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import revolusion.hotelmanagement.entity.Hotel;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    @Query("SELECT h FROM Hotel h WHERE (h.starRating <= ?1) OR (h.address ilike ?2%) OR (h.name ilike ?3%)")
    List<Hotel> findHotelsByFilter(Integer rating, String address, String name);
}