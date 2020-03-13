package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.controller;

import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.model.Ticket;
import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.service.TicketBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/tickets")
public class TicketBookingController {

    @Autowired
    private TicketBookingService ticketBookingService;

    @PostMapping(value = "/create")
    public Ticket createTicket(@RequestBody Ticket ticket){
       return ticketBookingService.createTicket(ticket);
    }

    @GetMapping(value = "/ticket/{ticketid}")
    public Ticket getTicketById(@PathVariable("ticketid") Integer ticketid){
        return  ticketBookingService.getTicketById(ticketid);
    }

    @GetMapping(value = "/ticket/allTickets")
    public List<Ticket> getAllTickets(){
        return ticketBookingService.getAllTickets();
    }

    @DeleteMapping("/delete/{ticketid}")
    public void deleteTicket(@PathVariable("ticketid") Integer ticketid){
         ticketBookingService.deleteTicket(ticketid);
    }

    @PutMapping("/update")
    public Ticket updateTicket(@RequestBody Ticket ticket){
        return  ticketBookingService.updateTicket(ticket);
    }

    @GetMapping("/ticketByDestStation")
    public List<Ticket> getTicketByDestStation(@RequestBody Ticket ticket){
        return  ticketBookingService.getTicketByDestStation(ticket);
    }

}
