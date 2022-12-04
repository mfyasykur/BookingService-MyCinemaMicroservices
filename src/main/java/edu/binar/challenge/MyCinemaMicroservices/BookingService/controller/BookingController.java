package edu.binar.challenge.MyCinemaMicroservices.BookingService.controller;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.converter.BookingConverter;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Booking;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.BookingDto;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.ResponseTemplateDto;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.exception.ResourceNotFoundEx;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/api/mycinema-v1")
public class BookingController {

    @Autowired
    private BookingService bookingService;

//    @GetMapping("/bookings/")
//    public List<BookingDto> getAllBookings() {
//        return bookingService.getAllBookings().stream().map(booking -> new ModelMapper().map(booking, BookingDto.class))
//                .toList();
//    }

    @GetMapping("/booking/")
    public ResponseTemplateDto getAllBooking(@RequestHeader(value = "id") Long id) throws ResourceNotFoundEx {

        return bookingService.getBookingWithUserAndShow(id);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<BookingDto> getBookingById(@PathVariable(value = "bookingId") Long bookingId)
            throws ResourceNotFoundEx {

        Booking booking = bookingService.getBookingById(bookingId);
        BookingDto bookingResponse = BookingConverter.convertEntityToDto(booking);

        return ResponseEntity.ok().body(bookingResponse);
    }

    @PostMapping("/booking")
    public ResponseEntity<BookingDto> createBooking(@Valid @RequestBody BookingDto bookingDto){

        Booking bookingRequest = BookingConverter.convertDtoToEntity(bookingDto);
        Booking booking = bookingService.createBooking(bookingRequest);
        BookingDto bookingResponse = BookingConverter.convertEntityToDto(booking);

        return new ResponseEntity<>(bookingResponse, HttpStatus.CREATED);
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<BookingDto> updateBooking(@PathVariable(value = "bookingId") Long bookingId, @Valid @RequestBody BookingDto bookingDto) throws ResourceNotFoundEx {

        Booking bookingRequest = BookingConverter.convertDtoToEntity(bookingDto);
        Booking booking = bookingService.updateBooking(bookingId, bookingRequest);
        BookingDto bookingResponse = BookingConverter.convertEntityToDto(booking);

        return new ResponseEntity<>(bookingResponse, HttpStatus.OK);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable(value = "bookingId") Long bookingId) throws ResourceNotFoundEx {
        bookingService.deleteBooking(bookingId);

        return ResponseEntity.ok().body("Booking with ID(" + bookingId + ") deleted successfully");
    }

    @GetMapping(value = "/secure")
    public String getSecure() {
        return "Secure endpoint available";
    }
}
