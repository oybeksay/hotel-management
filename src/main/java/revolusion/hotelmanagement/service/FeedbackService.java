package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import revolusion.hotelmanagement.dto.FeedbackDTO;
import revolusion.hotelmanagement.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback saveFeedback(FeedbackDTO feedbackDTO);

    Feedback getFeedbackById(Integer id);

    void deleteFeedbackById(Integer id);

    List<Feedback> getAllFeedbackByOrderId(Integer orderId);

    Page<Feedback> getAllFeedback(PageRequest pageRequest);
}
