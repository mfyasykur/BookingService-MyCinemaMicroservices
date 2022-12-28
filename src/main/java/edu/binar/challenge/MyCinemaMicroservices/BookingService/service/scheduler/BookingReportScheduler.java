package edu.binar.challenge.MyCinemaMicroservices.BookingService.service.scheduler;

import edu.binar.challenge.MyCinemaMicroservices.BookingService.entity.Booking;
import edu.binar.challenge.MyCinemaMicroservices.BookingService.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class BookingReportScheduler {

    @Autowired
    private BookingRepository bookingRepository;

    @Bean
    @Scheduled(cron = "0 12 * * SUN") //generate report every Sunday at 12 pm
    public void generateReport() throws FileNotFoundException, JRException {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
        String path = "./generated-reports";
        List<Booking> bookings = bookingRepository.findAll();

        File file = ResourceUtils.getFile("classpath:report/booking-report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bookings);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "MyCinema");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "/report-" + timeStamp + ".pdf");

        log.info("Generating report-" + timeStamp + ".pdf");
    }
}
