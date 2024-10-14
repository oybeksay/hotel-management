package revolusion.hotelmanagement.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.FeedbackDTO;
import revolusion.hotelmanagement.entity.Feedback;
import revolusion.hotelmanagement.service.FeedbackService;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<Page<Feedback>> getAllFeedback(Pageable pageable) {
        return ResponseEntity.ok(feedbackService.getAllFeedback(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Integer id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @PostMapping
    public ResponseEntity<Feedback> save(@RequestBody FeedbackDTO feedbackDTO) {
        return ResponseEntity.ok(feedbackService.saveFeedback(feedbackDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> update(@PathVariable Integer id, @RequestBody FeedbackDTO feedbackDTO) {
        return ResponseEntity.ok(feedbackService.updateFeedback(feedbackDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedbackById(@PathVariable Integer id) {
        feedbackService.deleteFeedbackById(id);
        return ResponseEntity.noContent().build();
    }
}
