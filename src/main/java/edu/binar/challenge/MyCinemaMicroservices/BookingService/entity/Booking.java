package edu.binar.challenge.MyCinemaMicroservices.BookingService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;

    @Column(name = "numberOfSeats", nullable = false)
    private int numberOfSeats;

    @Column(name = "timeStamp", nullable = false)
    private LocalDateTime timeStamp;

    public enum BookingStatus {
        SUCCESS,
        CANCELED
    }

    @Enumerated
    @Column(name = "status")
    private BookingStatus status;

    private Long userId;

    private Long showId;
}
