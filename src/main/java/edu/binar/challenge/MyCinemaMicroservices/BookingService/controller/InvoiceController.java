package edu.binar.challenge.MyCinemaMicroservices.BookingService.controller;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.converter.InvoiceConverter;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Invoice;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.InvoiceDto;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.exception.ResourceNotFoundEx;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.service.InvoiceService;
import lombok.Setter;
import net.sf.jasperreports.engine.JRException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Setter
@RestController
@RequestMapping("/api/mycinema-v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<InvoiceDto> createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {
        Invoice invoiceRequest = InvoiceConverter.convertDtoToEntity(invoiceDto);
        Invoice invoice = invoiceService.createInvoice(invoiceRequest);

        InvoiceDto invoiceResponse = InvoiceConverter.convertEntityToDto(invoice);

        return new ResponseEntity<>(invoiceResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public List<InvoiceDto> getAllInvoices(){
        return invoiceService
                .getAllInvoices()
                .stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDto.class)).toList();
    }

    @GetMapping("/find")
    public List<Invoice> getInvoicesByInvoiceNumber(@RequestParam(value = "invoiceNumber") String invoiceNumber) throws ResourceNotFoundEx {
        if (invoiceService.getInvoiceByInvoiceNumber(invoiceNumber).isEmpty()){
            throw new ResourceNotFoundEx("Invoice not found for this invoice number: " + invoiceNumber);
        }

        return invoiceService.getInvoiceByInvoiceNumber(invoiceNumber);
    }

    @GetMapping("/generate")
    public String generateInvoice(@RequestParam("invoiceNumber") String invoiceNumber, @RequestParam("fileFormat") String fileFormat) throws IOException, JRException {
        return invoiceService.generateInvoice(invoiceNumber, fileFormat);
    }
}
