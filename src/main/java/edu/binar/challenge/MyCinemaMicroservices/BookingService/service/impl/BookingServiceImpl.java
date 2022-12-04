package edu.binar.challenge.MyCinemaMicroservices.BookingService.service.impl;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Booking;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.ResponseTemplateDto;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.ShowDto;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.UserDto;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.exception.ResourceNotFoundEx;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.repository.BookingRepository;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private RestTemplate restTemplate;

    @Override
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    private static final String MESSAGE = "Booking not found for this id :: ";

    @Override
    public Booking getBookingById(Long bookingId) throws ResourceNotFoundEx {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundEx(MESSAGE + bookingId));

        return bookingRepository.save(booking);
    }

    @Override
    public Booking createBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long bookingId, Booking bookingDetails) throws ResourceNotFoundEx {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundEx(MESSAGE + bookingId));

        booking.setNumberOfSeats(bookingDetails.getNumberOfSeats());
        booking.setTimeStamp(bookingDetails.getTimeStamp());
        booking.setStatus(bookingDetails.getStatus());
        booking.setUserId(bookingDetails.getUserId());
        booking.setShowId(bookingDetails.getShowId());

        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) throws ResourceNotFoundEx {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundEx(MESSAGE + bookingId));

        bookingRepository.delete(booking);
    }

    @Override
    public ResponseTemplateDto getBookingWithUserAndShow(Long id) throws ResourceNotFoundEx {

        Booking booking = this.getBookingById(id);

        UserDto user = restTemplate.getForObject("http://user-service/users/" + booking.getUserId(), UserDto.class);

        ShowDto show = restTemplate.getForObject("http://cinema-service/cinemas/" + booking.getShowId(), ShowDto.class);

        return new ResponseTemplateDto(booking, user, show);
    }
}
