package revolusion.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import revolusion.hotelmanagement.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}