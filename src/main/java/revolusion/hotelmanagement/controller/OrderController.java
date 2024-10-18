package revolusion.hotelmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.OrderDTO;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Order", description = "the Order API")
@PreAuthorize("isAuthenticated()")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Get all orders", description = "Get all orders", tags = {"Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = PagedModel.class)))
    })
    @GetMapping
    public ResponseEntity<PagedModel<Order>> getAllOrder(@Parameter(description = "page number") @RequestParam(defaultValue = "0") Integer page,
                                                             @Parameter(description = "page size") @RequestParam(defaultValue = "10") Integer size) {
        Page<Order> orders = orderService.getAllOrderByPaged(PageRequest.of(page, size));
        PagedModel<Order> pagedModel = new PagedModel<>(orders);
        return ResponseEntity.ok(pagedModel);
    }

    @Operation(summary = "Get order by id", description = "Get order by id", tags = {"Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@Parameter(description = "order id") @PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @Operation(summary = "Get orders by guest id", description = "Get orders by guest id", tags = {"Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "Orders not found", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @GetMapping("/guest/{id}")
    public ResponseEntity<List<Order>> getOrdersByGuestId(@Parameter(description = "guest id") @PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrdersByGuestId(id));
    }

    @Operation(summary = "Create order", description = "Create order", tags = {"Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@Parameter(description = "orderDTO") @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(orderDTO));
    }

    @Operation(summary = "Update order", description = "Update order", tags = {"Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Order.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@Parameter(description = "order") @RequestBody Order order) {
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

    @Operation(summary = "Delete order", description = "Delete order", tags = {"Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation", content = @Content(schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content(schema = @Schema(implementation = Void.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@Parameter(description = "order id") @PathVariable Integer id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}