package edu.binar.challenge.MyCinemaMicroservices.BookingService.service;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Payment;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.exception.ResourceNotFoundEx;

import java.util.List;

public interface PaymentService {

    List<Payment> getAllPayments();
    Payment getPaymentById(Long paymentId) throws ResourceNotFoundEx;
    Payment createPayment(Payment payment);
    Payment updatePayment(Long paymentId, Payment payment) throws ResourceNotFoundEx;
    void deletePayment(Long paymentId) throws ResourceNotFoundEx;
}
