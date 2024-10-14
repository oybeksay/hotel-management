package revolusion.hotelmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import revolusion.hotelmanagement.dto.PaymentDTO;
import revolusion.hotelmanagement.entity.Payment;
import revolusion.hotelmanagement.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<PagedModel<Payment>> getAllPaymentByPage(@PathVariable Integer page,
                                                                   @PathVariable Integer size) {
        Page<Payment> payments = paymentService.getAllPayment(PageRequest.of(page, size));
        PagedModel<Payment> pagedModel = new PagedModel<>(payments);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.savePayment(paymentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.updatePayment(payment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable Integer id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.noContent().build();
    }
}