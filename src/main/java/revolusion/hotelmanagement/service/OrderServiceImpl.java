package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.OrderDTO;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.entity.Room;
import revolusion.hotelmanagement.mapper.OrderMapper;
import revolusion.hotelmanagement.repository.GuestRepository;
import revolusion.hotelmanagement.repository.OrderRepository;
import revolusion.hotelmanagement.repository.RoomRepository;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final OrderRepository orderRepository;


    @Override
    public Order saveOrder(OrderDTO orderDTO) {
        Room room = roomRepository.findById(orderDTO.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        Guest guest = guestRepository.findById(orderDTO.getGuestId()).orElseThrow(() -> new RuntimeException("Guest not found"));
        List<Order> conflictingOrders = orderRepository.findConflictingOrders(
                orderDTO.getRoomId(),
                orderDTO.getStartDate(),
                orderDTO.getEndDate());

        if (!conflictingOrders.isEmpty()) {
            throw new RuntimeException("Conflicting orders found");
        }

        Order order = orderMapper.fromDTO(orderDTO, guest, room);
        order.setTotalAmount(calculateTotalAmount(orderDTO));
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public void deleteOrderById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Page<Order> getAllOrderByPaged(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public List<Order> getOrdersByGuestId(Integer id) {
        return orderRepository.findByGuest_Id(id);
    }

    @Override
    public double calculateTotalAmount(OrderDTO orderDTO) {
        long numberOfNights = ChronoUnit.DAYS.between(orderDTO.getStartDate(), orderDTO.getEndDate());
        Room room = roomRepository.findById(orderDTO.getRoomId()).orElse(null);
        assert room != null;
        return numberOfNights * room.getPricePerNight();
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }
}
