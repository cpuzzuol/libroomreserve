package com.ucrisko.libroomreserve.core.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Reservation {
    private Long reservationId;
    private Date startTime;
    private Date endTime;
    private User user;
    private Room room;
    private String note;

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", nullable=false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="roomId", nullable=false)
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Column
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
