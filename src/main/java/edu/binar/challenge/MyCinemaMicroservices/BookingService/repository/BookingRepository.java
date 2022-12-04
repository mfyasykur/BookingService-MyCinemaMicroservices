package edu.binar.challenge.MyCinemaMicroservices.BookingService.repository;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
