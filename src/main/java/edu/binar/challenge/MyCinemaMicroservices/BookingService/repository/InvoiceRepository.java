package edu.binar.challenge.MyCinemaMicroservices.BookingService.repository;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByInvoiceNumberOrderByCreatedAtDesc(String invoiceNumber);

    boolean existsByInvoiceNumber(String invoiceNumber);
}
