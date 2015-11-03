/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Room {
    private Long roomId;
    private String roomNumber;
    private String roomDescription;
    private Integer roomCapacity;
    private List<Reservation> reservations;

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    
    @Column(unique=true, nullable=false)
    public String getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    @Column
    public String getRoomDescription() {
        return roomDescription;
    }
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    @Column
    public Integer getRoomCapacity() {
        return roomCapacity;
    }
    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
    
    //mappedBy takes the name of the field declared in the Many class
    @OneToMany(fetch=FetchType.LAZY, mappedBy="room")
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
