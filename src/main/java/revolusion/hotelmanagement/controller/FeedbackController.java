package revolusion.hotelmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.FeedbackDTO;
import revolusion.hotelmanagement.entity.Feedback;
import revolusion.hotelmanagement.service.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "the feedback API")
@PreAuthorize("isAuthenticated()")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @Operation(summary = "Get a feedback by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Feedback.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@Parameter(description = "id of the feedback to be obtained. Cannot be empty.") @PathVariable Integer id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }

    @Operation(summary = "Get all feedbacks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Page.class)))
    })
    @GetMapping
    public ResponseEntity<PagedModel<Feedback>> getAllFeedback(@Parameter(description = "page number, starting from 0") @RequestParam Integer page, @Parameter(description = "size of the page, must be greater than 0") @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Feedback> allFeedback = feedbackService.getAllFeedback(pageRequest);
        PagedModel<Feedback> pageModel = new PagedModel<>(allFeedback);
        return ResponseEntity.ok(pageModel);
    }

    @Operation(summary = "Get all feedbacks by order id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = List.class)))
    })
    @GetMapping("/order/{id}")
    public ResponseEntity<List<Feedback>> getAllFeedbackByOrderId(@Parameter(description = "id of the order to be obtained. Cannot be empty.") @PathVariable Integer id) {
        return ResponseEntity.ok(feedbackService.getAllFeedbackByOrderId(id));
    }

    @Operation(summary = "Create a new feedback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Feedback.class)))
    })
    @PostMapping("/create")
    public ResponseEntity<Feedback> saveFeedBack(@Parameter(description = "feedback to be created") @RequestBody FeedbackDTO feedbackDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feedbackService.saveFeedback(feedbackDTO));
    }


    @Operation(summary = "Delete a feedback")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "successful operation")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedbackById(@Parameter(description = "id of the feedback to be deleted. Cannot be empty.") @PathVariable Integer id) {
        feedbackService.deleteFeedbackById(id);
        return ResponseEntity.noContent().build();
    }
}