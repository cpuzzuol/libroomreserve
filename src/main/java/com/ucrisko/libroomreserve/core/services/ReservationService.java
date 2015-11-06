/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.services;

import com.ucrisko.libroomreserve.core.entities.Reservation;
import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.core.entities.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cpuzzuol
 */
public interface ReservationService {
    public Reservation addReservation(Reservation reservation);
    public Reservation editReservation(Reservation reservation);
    public void deleteReservation(Long reservationId);
    
    public Reservation getReservationById(Long reservationId);
    
    public List<Reservation> getReservationsByDate(Date startOrEndDate, boolean start);
    public List<Reservation> getReservationsByDate(Date startDate, Date endDate);
    
    public List<Reservation> getReservationsByUser(User user);
    public List<Reservation> getReservationsByUser(User user, Date startOrEndDate, boolean start);
    public List<Reservation> getReservationsByUser(User user, Date startDate, Date endDate);
    
    public List<Reservation> getReservationsByRoom(Room room);
    public List<Reservation> getReservationsByRoom(Room room, Date startOrEndDate, boolean start);
    public List<Reservation> getReservationsByRoom(Room room, Date startDate, Date endDate);
    
    public Reservation getReservationByTimeRoom(Date startTime, Room room);
}
