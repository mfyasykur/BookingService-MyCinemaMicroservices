package edu.binar.challenge.MyCinemaMicroservices.BookingService.converter;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Booking;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.BookingDto;
import org.modelmapper.ModelMapper;

public class BookingConverter {

    private BookingConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Booking convertDtoToEntity(BookingDto bookingDto){
        return modelMapper.map(bookingDto, Booking.class);
    }

    public static BookingDto convertEntityToDto(Booking booking){
        return modelMapper.map(booking, BookingDto.class);
    }
}
