package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="ticket")
/*@NamedQueries(value = {
        @NamedQuery(name = "Ticket.findByDestStationInfo", query ="select t from Ticket t where t.destStation=?1")
})*/

/*@NamedNativeQueries(value = {
        @NamedNativeQuery(name="Ticket.findByDestStationInfo", query ="select * from ticket where dest_Station=?1",resultClass = Ticket.class)
})*/
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ticket_id")
    private Integer ticketId;

    @Column(name = "passenger_name",length = 60,nullable = false)
    private String passengerName;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "source_station")
    private String sourceStation;

    @Column(name="dest_station")
    private String destStation;

    @Column(name = "email",unique = true)
    private String email;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public String getDestStation() {
        return destStation;
    }

    public void setDestStation(String destStation) {
        this.destStation = destStation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", passengerName='" + passengerName + '\'' +
                ", bookingDate=" + bookingDate +
                ", sourceStation='" + sourceStation + '\'' +
                ", destStation='" + destStation + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
