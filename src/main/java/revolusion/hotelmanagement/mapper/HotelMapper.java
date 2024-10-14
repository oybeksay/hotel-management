package revolusion.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import revolusion.hotelmanagement.dto.HotelDTO;
import revolusion.hotelmanagement.entity.Hotel;

@Mapper(componentModel = "spring")
public interface HotelMapper {
    Hotel fromDTO(HotelDTO hotelDTO);
}
