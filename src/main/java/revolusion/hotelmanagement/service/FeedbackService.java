package revolusion.hotelmanagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import revolusion.hotelmanagement.dto.FeedbackDTO;
import revolusion.hotelmanagement.entity.Feedback;

public interface FeedbackService {
    Feedback saveFeedback(FeedbackDTO feedbackDTO);
    Feedback updateFeedback(Feedback feedback);
    Feedback getFeedbackById(Integer id);
    void deleteFeedbackById(Integer id);
    Page<Feedback> getAllFeedback(Pageable pageable);
}
