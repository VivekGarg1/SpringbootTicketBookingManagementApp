package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp;


import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.config.EnvBasedConfig;
import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.model.Ticket;
import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.service.TicketBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@SpringBootApplication(scanBasePackages = "com.home.SpringbootCrudWithEmbeddedDB.*")
@EnableAsync
public class SpringbootTicketBookingManagementAppApplication implements CommandLineRunner/*, ErrorController*/ {

	private static final Logger logger= LoggerFactory.getLogger(SpringbootTicketBookingManagementAppApplication.class);
	//private static final String path="/error";

	@Autowired
	private TicketBookingService ticketBookingService;

	@Autowired
	private EnvBasedConfig envBasedConfig;

	@Value("${welcome.message}")
	private String message;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTicketBookingManagementAppApplication.class, args);
		logger.error("Message logged at error level");
		logger.warn("Message logged at warn level");
		logger.info("Message logged at info level");
		logger.debug("Message logged at debug level");
	}

	@RequestMapping("/")
	public String welcome(){
		return message;
	}

	/*@GetMapping(value = path)
	public String defaultError(){
		return "Requested resource is not found!!!";
	}

	@Override
	public String getErrorPath() {
		return path;
	}*/

	@Override
	public void run(String... args) throws Exception {
		getTicketByDestStationInfo();
		getTicketByEmail();
		getTicketByDestOrSrcStation();
		updateEmailById();
		envBasedConfig.setUp();
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
