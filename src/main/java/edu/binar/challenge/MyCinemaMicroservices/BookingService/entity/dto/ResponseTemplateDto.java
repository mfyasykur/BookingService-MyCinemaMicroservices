package edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseTemplateDto {

    private Booking booking;

    private UserDto user;

    private ShowDto show;
}
