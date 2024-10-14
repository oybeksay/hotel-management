package revolusion.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import revolusion.hotelmanagement.dto.AuthUserDTO;
import revolusion.hotelmanagement.entity.auth.AuthUser;

@Mapper(componentModel = "spring")
public interface AuthUserMapper {

    AuthUser fromDTO(AuthUserDTO authUserDTO);

}
