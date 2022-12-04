package edu.binar.challenge.MyCinemaMicroservices.BookingService.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @Column(name = "amount", nullable = false)
    private Number amount;

    @Column(name = "timeStamp", nullable = false)
    private ZonedDateTime timeStamp;

    public enum paymentMethod {
        CASH,
        BANK,
        E_WALLET
    }

    @Enumerated
    @Column(name = "method")
    private paymentMethod method;

    @ManyToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;
}
