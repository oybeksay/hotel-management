package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import revolusion.hotelmanagement.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}