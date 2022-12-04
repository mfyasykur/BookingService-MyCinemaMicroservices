package edu.binar.challenge.MyCinemaMicroservices.BookingService.service.impl;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Payment;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.exception.ResourceNotFoundEx;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.repository.PaymentRepository;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    private static final String MESSAGE = "CinemaHall not found for this id :: ";

    @Override
    public Payment getPaymentById(Long paymentId) throws ResourceNotFoundEx {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundEx(MESSAGE + paymentId));

        return paymentRepository.save(payment);
    }

    @Override
    public Payment createPayment(Payment payment){
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Long paymentId, Payment paymentDetails) throws ResourceNotFoundEx {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundEx(MESSAGE + paymentId));

        payment.setAmount(paymentDetails.getAmount());
        payment.setTimeStamp(paymentDetails.getTimeStamp());
        payment.setMethod(paymentDetails.getMethod());
        payment.setBooking(paymentDetails.getBooking());

        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long paymentId) throws ResourceNotFoundEx {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundEx(MESSAGE + paymentId));

        paymentRepository.delete(payment);
    }
}
