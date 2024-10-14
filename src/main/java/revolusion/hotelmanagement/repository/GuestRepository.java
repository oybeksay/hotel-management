package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import revolusion.hotelmanagement.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
}