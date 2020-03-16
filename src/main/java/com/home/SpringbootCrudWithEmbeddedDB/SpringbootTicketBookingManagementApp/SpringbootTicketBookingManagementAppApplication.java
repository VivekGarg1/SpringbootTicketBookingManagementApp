package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp;


import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.model.Ticket;
import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.service.TicketBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@SpringBootApplication
@EnableAsync
public class SpringbootTicketBookingManagementAppApplication implements CommandLineRunner {

	private static final Logger logger= LoggerFactory.getLogger(SpringbootTicketBookingManagementAppApplication.class);

	@Autowired
	private TicketBookingService ticketBookingService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTicketBookingManagementAppApplication.class, args);
		logger.error("Message lohgged at error level");
		logger.warn("Message lohgged at warn level");
		logger.info("Message lohgged at info level");
		logger.debug("Message lohgged at debug level");
	}

	@RequestMapping("/")
	public String welcome(){
		logger.info("Message lohgged at info level");
		return "Hello World!!!";
		}

	@Override
	public void run(String... args) throws Exception {
		getTicketByDestStationInfo();
		getTicketByEmail();
		getTicketByDestOrSrcStation();
		updateEmailById();
	}

	private void updateEmailById() {
		int id=3;
		String newEmail="shivam.arora@hays.com";
		ticketBookingService.updateEmailById(id,newEmail);
	}

	private void getTicketByDestOrSrcStation() {
		List<Ticket> ticketList = ticketBookingService.findByDestOrSourceStation("Noida","Delhi");
		ticketList.forEach(System.out::println);
	}

	public void getTicketByDestStationInfo(){
		List<Ticket> ticketList = ticketBookingService.getTicketByDestStationInfo("Noida",PageRequest.of(0,4, Sort.Direction.ASC,"passengerName"));
		ticketList.forEach(System.out::println);
	}

	private void getTicketByEmail() throws InterruptedException, ExecutionException, TimeoutException {
		CompletableFuture<Ticket> ticketCompletableFuture = ticketBookingService.findByEmail("vivek.garg@gmail.com");
		Ticket ticket=ticketCompletableFuture.get(20, TimeUnit.SECONDS);
		System.out.println(ticket);
	}


}
