package revolusion.hotelmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import revolusion.hotelmanagement.domein.PaymentMethod;
import revolusion.hotelmanagement.domein.PaymentStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentDTO {
    private Double amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private Integer guestId;
    private Integer orderId;
}
