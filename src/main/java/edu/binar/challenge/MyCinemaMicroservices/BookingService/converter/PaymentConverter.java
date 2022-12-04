package edu.binar.challenge.MyCinemaMicroservices.BookingService.converter;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Payment;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.PaymentDto;
import org.modelmapper.ModelMapper;

public class PaymentConverter {

    private PaymentConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Payment convertDtoToEntity(PaymentDto paymentDto){
        return modelMapper.map(paymentDto, Payment.class);
    }

    public static PaymentDto convertEntityToDto(Payment payment){
        return modelMapper.map(payment, PaymentDto.class);
    }
}
