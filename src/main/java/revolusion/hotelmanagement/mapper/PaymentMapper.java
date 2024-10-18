package revolusion.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import revolusion.hotelmanagement.dto.PaymentDTO;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.entity.Payment;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guest", source = "guest")
    @Mapping(target = "order", source = "order")
    @Mapping(target = "status", source = "paymentDTO.status")
    Payment fromDTO(PaymentDTO paymentDTO, Order order, Guest guest);

}
