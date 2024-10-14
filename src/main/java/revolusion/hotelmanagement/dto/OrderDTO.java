package revolusion.hotelmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import revolusion.hotelmanagement.domein.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDTO {
    private Integer guestId;
    private Integer roomId;
    private LocalDate startDate;
    private LocalDate endDate;
    private OrderStatus status;
    private Double totalAmount;
}
