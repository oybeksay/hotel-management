package revolusion.hotelmanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import revolusion.hotelmanagement.dto.FeedbackDTO;
import revolusion.hotelmanagement.entity.Feedback;
import revolusion.hotelmanagement.entity.Guest;
import revolusion.hotelmanagement.entity.Order;
import revolusion.hotelmanagement.mapper.FeedbackMapper;
import revolusion.hotelmanagement.repository.FeedbackRepository;
import revolusion.hotelmanagement.repository.GuestRepository;
import revolusion.hotelmanagement.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;
    private final OrderRepository orderRepository;
    private final GuestRepository guestRepository;

    @Override
    public Feedback saveFeedback(FeedbackDTO feedbackDTO) {
        Order order = orderRepository.findById(feedbackDTO.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
        Guest guest = guestRepository.findById(feedbackDTO.getGuestId()).orElseThrow(() -> new RuntimeException("Guest not found"));
        Feedback feedback = feedbackMapper.fromDTO(feedbackDTO, guest, order);
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Integer id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    @Override
    public void deleteFeedbackById(Integer id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public List<Feedback> getAllFeedbackByOrderId(Integer orderId) {
        return feedbackRepository.findAllByOrderId(orderId);
    }

    @Override
    public Page<Feedback> getAllFeedback(PageRequest pageRequest) {
        return feedbackRepository.findAll(pageRequest);
    }

}
