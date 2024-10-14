package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import revolusion.hotelmanagement.dto.OrderDTO;
import revolusion.hotelmanagement.entity.Order;

public interface OrderService {
    Order saveOrder(OrderDTO orderDTO);
    Order updateOrder(Order order);
    Order getOrderById(Integer id);
    void deleteOrderById(Integer id);
    Page<Order> getAllOrder(Pageable pageable);
}
