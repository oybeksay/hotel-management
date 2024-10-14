package revolusion.hotelmanagement.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.OrderDTO;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<PagedModel<Order>> getAllOrders(@RequestParam(defaultValue = "0") Integer page,
                                                             @RequestParam(defaultValue = "10") Integer size) {
        Page<Order> orders = orderService.getAllOrder(PageRequest.of(page, size));
        PagedModel<Order> pagedModel = new PagedModel<>(orders);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.saveOrder(orderDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
