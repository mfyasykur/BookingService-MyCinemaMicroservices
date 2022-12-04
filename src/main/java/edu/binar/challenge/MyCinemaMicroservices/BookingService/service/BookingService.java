package edu.binar.challenge.MyCinemaMicroservices.BookingService.service;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Booking;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.ResponseTemplateDto;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.exception.ResourceNotFoundEx;

import java.util.List;

public interface BookingService {

    List<Booking> getAllBookings();
    Booking getBookingById(Long bookingId) throws ResourceNotFoundEx;
    Booking createBooking(Booking booking);
    Booking updateBooking(Long bookingId, Booking booking) throws ResourceNotFoundEx;
    void deleteBooking(Long bookingId) throws ResourceNotFoundEx;
    ResponseTemplateDto getBookingWithUserAndShow(Long id) throws ResourceNotFoundEx;
}
