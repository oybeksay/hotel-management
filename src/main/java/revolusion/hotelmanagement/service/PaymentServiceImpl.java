package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.PaymentDTO;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.entity.Payment;
import revolusion.hotelmanagement.mapper.PaymentMapper;
import revolusion.hotelmanagement.repository.GuestRepository;
import revolusion.hotelmanagement.repository.OrderRepository;
import revolusion.hotelmanagement.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final OrderRepository orderRepository;
    private final GuestRepository guestRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(PaymentDTO paymentDTO) {
        Order order = orderRepository.findById(paymentDTO.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
        Guest guest = guestRepository.findById(paymentDTO.getGuestId()).orElseThrow(() -> new RuntimeException("Guest not found"));
        Payment payment = paymentMapper.fromDTO(paymentDTO, order, guest);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public void deletePaymentById(Integer id) {
        paymentRepository.deleteById(id);
    }
}
