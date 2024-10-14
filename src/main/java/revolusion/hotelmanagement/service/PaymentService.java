package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import revolusion.hotelmanagement.dto.PaymentDTO;
import revolusion.hotelmanagement.entity.Payment;

public interface PaymentService {
    Payment savePayment(PaymentDTO paymentDTO);
    Payment updatePayment(Payment payment);
    Payment getPaymentById(Integer id);
    void deletePaymentById(Integer id);
    Page<Payment> getAllPayment(Pageable pageable);
}
