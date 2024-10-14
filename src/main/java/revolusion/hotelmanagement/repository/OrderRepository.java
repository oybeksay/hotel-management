package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import revolusion.hotelmanagement.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}