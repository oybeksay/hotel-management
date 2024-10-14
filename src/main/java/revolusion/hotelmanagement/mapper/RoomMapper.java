package revolusion.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import revolusion.hotelmanagement.dto.RoomDTO;
import revolusion.hotelmanagement.entity.Room;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room fromDTO(RoomDTO roomDTO);
}
