package edu.binar.challenge.MyCinemaMicroservices.BookingService.converter;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Invoice;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.dto.InvoiceDto;
import org.modelmapper.ModelMapper;

public class InvoiceConverter {

    private InvoiceConverter() {
        throw new IllegalStateException();
    }

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Invoice convertDtoToEntity(InvoiceDto invoiceDto){
        return modelMapper.map(invoiceDto, Invoice.class);
    }

    public static InvoiceDto convertEntityToDto(Invoice invoice){
        return modelMapper.map(invoice, InvoiceDto.class);
    }
}
