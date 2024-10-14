package revolusion.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import revolusion.hotelmanagement.dto.OrderDTO;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.entity.Room;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guest", source = "guest")
    @Mapping(target = "room", source = "room")
    Order  fromDTO(OrderDTO orderDTO, Guest guest, Room room);
}
