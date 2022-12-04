package edu.binar.challenge.MyCinemaMicroservices.BookingService.controller;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.converter.PaymentConverter;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Payment;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.PaymentDto;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.exception.ResourceNotFoundEx;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.service.PaymentService;
import javax.validation.Valid;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Setter
@RestController
@RequestMapping("/api/mycinema-v1")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments/")
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments().stream().map(payment -> new ModelMapper().map(payment, PaymentDto.class))
                .toList();
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable(value = "paymentId") Long paymentId)
            throws ResourceNotFoundEx {

        Payment payment = paymentService.getPaymentById(paymentId);
        PaymentDto paymentResponse = PaymentConverter.convertEntityToDto(payment);

        return ResponseEntity.ok().body(paymentResponse);
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto paymentDto){

        Payment paymentRequest = PaymentConverter.convertDtoToEntity(paymentDto);
        Payment payment = paymentService.createPayment(paymentRequest);
        PaymentDto paymentResponse = PaymentConverter.convertEntityToDto(payment);

        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/payment/{paymentId}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable(value = "paymentId") Long paymentId, @Valid @RequestBody PaymentDto paymentDto) throws ResourceNotFoundEx {

        Payment paymentRequest = PaymentConverter.convertDtoToEntity(paymentDto);
        Payment payment = paymentService.updatePayment(paymentId, paymentRequest);
        PaymentDto paymentResponse = PaymentConverter.convertEntityToDto(payment);

        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
    }

    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable(value = "paymentId") Long paymentId) throws ResourceNotFoundEx {
        paymentService.deletePayment(paymentId);

        return ResponseEntity.ok().body("Payment with ID(" + paymentId + ") deleted successfully");
    }
}
