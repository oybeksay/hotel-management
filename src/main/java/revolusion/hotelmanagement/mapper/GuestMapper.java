package revolusion.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import revolusion.hotelmanagement.dto.GuestDTO;
import revolusion.hotelmanagement.entity.Guest;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    Guest fromDTO(GuestDTO guestDTO);

}
