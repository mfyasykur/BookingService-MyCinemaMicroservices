package edu.binar.challenge.MyCinemaMicroservices.BookingService.service;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Invoice;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.exception.ResourceNotFoundEx;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public interface InvoiceService {

    @Autowired
    Invoice createInvoice(Invoice invoice);

    @Autowired
    String generateInvoice(String invoiceNumber, String fileFormat) throws JRException, IOException;

    @Autowired
    List<Invoice> getInvoiceByInvoiceNumber(String invoiceNumber) throws ResourceNotFoundEx;

    @Autowired
    List<Invoice> getAllInvoices();
}
