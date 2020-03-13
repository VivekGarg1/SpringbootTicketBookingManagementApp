package com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.dao;

import com.home.SpringbootCrudWithEmbeddedDB.SpringbootTicketBookingManagementApp.model.Ticket;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface TicketBookingDao extends JpaRepository<Ticket,Integer> {
    //Data get by method
   // List<Ticket> findByDestStation(String destStation);

    //@NamedQueries and @namedNativeQueries Example
    //List<Ticket> findByDestStationInfo(String destStation);

    //Using @Query by JPQL
    //@Query(value = "select t from Ticket t where t.destStation=?1")
    //or using plain sql query
    @Query(value = "select * from ticket where dest_Station=?1",nativeQuery = true)
    List<Ticket> findByDestStationInfo(String destStation);

    List<Ticket> findByDestStation(String destStation, Pageable pageable);

    @Async
    CompletableFuture<Ticket> findByEmail(String email);

    @Query("select t from Ticket t where t.destStation=:destStation or t.sourceStation=:srcStation")
    List<Ticket> findByDestOrSourceStation(@Param("destStation") String destStation,@Param("srcStation") String srcStation);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Ticket set email=:newEmail where ticketId=:id")
    void updateEmailById(@Param("id") int id,@Param("newEmail") String newEmail);
}
