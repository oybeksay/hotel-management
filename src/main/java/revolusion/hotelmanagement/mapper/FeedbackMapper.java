package revolusion.hotelmanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import revolusion.hotelmanagement.dto.FeedbackDTO;
import revolusion.hotelmanagement.entity.Feedback;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Order;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "guest", source = "guest")
    @Mapping(target = "order", source = "order")
    Feedback fromDTO(FeedbackDTO feedbackDTO, Guest guest, Order order);

}
