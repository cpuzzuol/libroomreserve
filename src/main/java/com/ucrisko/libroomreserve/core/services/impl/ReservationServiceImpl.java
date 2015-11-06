/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.services.impl;

import com.ucrisko.libroomreserve.core.dao.ReservationDao;
import com.ucrisko.libroomreserve.core.dao.RoomDao;
import com.ucrisko.libroomreserve.core.dao.UserDao;
import com.ucrisko.libroomreserve.core.entities.Reservation;
import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.core.services.ReservationService;
import com.ucrisko.libroomreserve.core.services.exceptions.ReservationExistsException;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationDao reservationDao;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired 
    private RoomDao roomDao;
    
    //error logger
    final static Logger logger = Logger.getLogger(RoomServiceImpl.class);
    
    @Override
    public Reservation addReservation(Reservation reservation){
        logger.debug("!!!IN ADDRESEVATOIN METHOD!!!");
        logger.debug(reservation.getRoom().toString());
        
        Reservation existingReservation = reservationDao.getReservationByTimeRoom(reservation.getStartTime(), reservation.getRoom());
        //Reservation existingReservation = null;
        if(existingReservation != null){
            logger.error("!!!THAT RESERVATION ALREADY EXISTS!!!");
            throw new ReservationExistsException("That reservation already exsits");
        }
        
        reservationDao.addReservation(reservation);
        return reservation;
    }

    @Override
    public Reservation editReservation(Reservation reservation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteReservation(Long reservationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getReservationsByDate(Date startOrEndDate, boolean start) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getReservationsByDate(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getReservationsByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getReservationsByUser(User user, Date startOrEndDate, boolean start) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getReservationsByUser(User user, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getReservationsByRoom(Room room) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getReservationsByRoom(Room room, Date startOrEndDate, boolean start) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getReservationsByRoom(Room room, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation getReservationByTimeRoom(Date startTime, Room room) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
