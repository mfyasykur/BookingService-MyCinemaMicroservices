package edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(
        value = {"cinemaId"},
        allowGetters = true
)
@Data
public class CinemaDto {

    @JsonProperty("cinemaId")
    @JsonIgnore
    private Long cinemaId;

    private String name;

    private int totalCinemaHalls;

    private CityDto city;
}
