package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.service;

import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.dao.TicketBookingDao;
import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TicketBookingService {

    @Autowired
    private TicketBookingDao ticketBookingDao;

    public Ticket createTicket(Ticket ticket) {
        return ticketBookingDao.save(ticket);
    }

    public Ticket getTicketById(Integer ticketid) {
        return ticketBookingDao.findById(ticketid).orElse(null);
    }

    public List<Ticket> getAllTickets() {
        return ticketBookingDao.findAll();
    }

    public void deleteTicket(Integer ticketid) {
        ticketBookingDao.deleteById(ticketid);
    }

    public Ticket updateTicket(Ticket ticket) {
        Ticket ticketfromDb = ticketBookingDao.findById(ticket.getTicketId()).orElse(null);
        if (ticketfromDb != null) {
            ticketfromDb.setPassengerName(ticket.getPassengerName());
            ticketfromDb.setEmail(ticket.getEmail());
            ticketfromDb.setSourceStation(ticket.getSourceStation());
            ticketfromDb.setDestStation(ticket.getDestStation());
            ticketfromDb.setBookingDate(ticket.getBookingDate());
        }
        return ticketBookingDao.save(ticketfromDb);
    }

    public List<Ticket> getTicketByDestStation(Ticket ticket) {
        return ticketBookingDao.findByDestStationInfo(ticket.getDestStation());
    }

    public List<Ticket> getTicketByDestStationInfo(String destStation, PageRequest pageRequest) {
        return ticketBookingDao.findByDestStation(destStation, pageRequest);
    }

    public CompletableFuture<Ticket> findByEmail(String email) {
        return ticketBookingDao.findByEmail(email);
    }

    public List<Ticket> findByDestOrSourceStation(String destStation, String srcStation) {
        return ticketBookingDao.findByDestOrSourceStation(destStation,srcStation);
    }
}
