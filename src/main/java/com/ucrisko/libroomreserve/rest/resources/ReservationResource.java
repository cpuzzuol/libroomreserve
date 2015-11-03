/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.resources;

import com.ucrisko.libroomreserve.core.entities.Reservation;
import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.core.entities.User;
import java.util.Date;
import org.springframework.hateoas.ResourceSupport;

/**
 *
 * @author cpuzzuol
 */
public class ReservationResource extends ResourceSupport{
    private Long reservationId;
    private Date startTime;
    private Date endTime;
    private User user;
    private Room room;
    private String note;

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public Reservation toReservation(){
        Reservation reservation = new Reservation();
        
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setRoom(room);
        reservation.setUser(user);
        reservation.setNote(note);
        
        return reservation;
    }
}
