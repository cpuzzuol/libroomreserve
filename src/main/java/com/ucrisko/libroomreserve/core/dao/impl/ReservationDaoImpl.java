/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.dao.impl;

import com.ucrisko.libroomreserve.core.dao.ReservationDao;
import com.ucrisko.libroomreserve.core.entities.Reservation;
import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.core.entities.User;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDaoImpl implements ReservationDao{
    private static Logger logger = Logger.getLogger(ReservationDaoImpl.class);
    
    @Autowired
    private SessionFactory session;
    
    @Override
    public Reservation addReservation(Reservation reservation) throws IllegalArgumentException{
        session.getCurrentSession().save(reservation);
        return reservation;
    }

    @Override
    public Reservation editReservation(Reservation reservation) {
        session.getCurrentSession().update(reservation);
        return reservation;
    }

    @Override
    public void deleteReservation(Long reservationId) {
        session.getCurrentSession().delete(getReservationById(reservationId));
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        logger.debug("!!!RESERVATION ID IS: " + reservationId);
        return (Reservation)session.getCurrentSession().get(Reservation.class, reservationId);
    }

    @Override
    public List<Reservation> getReservationsByDate(Date startOrEndDate, boolean start) {
        String matchingReservations;
        
        if(start){
            matchingReservations = "FROM Reservation r WHERE r.startTime >= :date";
        } else {
            matchingReservations = "FROM Reservation r WHERE r.endTime <= :date";
        }
        Query query = session.getCurrentSession().createQuery(matchingReservations);
        query.setParameter("date", startOrEndDate);
        
        List listReservations = query.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return listReservations;
    }

    @Override
    public List<Reservation> getReservationsByDate(Date startDate, Date endDate) {
        String matchingReservations = "FROM Reservation r WHERE r.startTime >= :startDate AND r.endTime <= :endDate";

        Query query = session.getCurrentSession().createQuery(matchingReservations);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        
        List listReservations = query.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return listReservations;
    }

    @Override
    public List<Reservation> getReservationsByUser(User user) {
        Long userId = user.getUserId();
        String matchingReservations = "FROM Reservation r WHERE r.userId = :userId";
        Query query = session.getCurrentSession().createQuery(matchingReservations);
        query.setParameter("userId", userId);
        
        List listReservations = query.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return listReservations;
    }

    @Override
    public List<Reservation> getReservationsByUser(User user, Date startOrEndDate, boolean start) {
        String matchingReservations;
        
        Long userId = user.getUserId();
        if(start){
            matchingReservations = "FROM Reservation r WHERE r.userId = :userId AND r.startTime >= :startOrEndDate";
        } else {
            matchingReservations = "FROM Reservation r WHERE r.userId = :userId AND r.endTime <= :startOrEndDate";
        }
        Query query = session.getCurrentSession().createQuery(matchingReservations);
        query.setParameter("userId", userId);
        query.setParameter("startOrEndDate", startOrEndDate);
        
        List listReservations = query.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return listReservations;
    }

    @Override
    public List<Reservation> getReservationsByUser(User user, Date startDate, Date endDate) {
        Long userId = user.getUserId();
        String matchingReservations = "FROM Reservation r WHERE r.userId = :userId AND r.startTime >= :startDate AND r.endTime <= :endDate";
        Query query = session.getCurrentSession().createQuery(matchingReservations);
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        
        List listReservations = query.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return listReservations;
    }

    @Override
    public List<Reservation> getReservationsByRoom(Room room) {
        Long roomId = room.getRoomId();
        String matchingReservations = "FROM Reservation r WHERE r.roomId = :roomId";
        Query query = session.getCurrentSession().createQuery(matchingReservations);
        query.setParameter("roomId", roomId);
        
        List listReservations = query.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return listReservations;
    }

    @Override
    public List<Reservation> getReservationsByRoom(Room room, Date startOrEndDate, boolean start) {
        String matchingReservations;
        
        Long roomId = room.getRoomId();
        if(start){
            matchingReservations = "FROM Reservation r WHERE r.roomId = :roomId AND r.startTime >= :startOrEndDate";
        } else {
            matchingReservations = "FROM Reservation r WHERE r.roomId = :roomId AND r.endTime <= :startOrEndDate";
        }
        Query query = session.getCurrentSession().createQuery(matchingReservations);
        query.setParameter("roomId", roomId);
        query.setParameter("startOrEndDate", startOrEndDate);
        
        List listReservations = query.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return listReservations;
    }

    @Override
    public List<Reservation> getReservationsByRoom(Room room, Date startDate, Date endDate) {
        Long roomId = room.getRoomId();
        String matchingReservations = "FROM Reservation r WHERE r.roomId = :roomId AND r.startTime >= :startDate AND r.endTime <= :endDate";
        Query query = session.getCurrentSession().createQuery(matchingReservations);
        query.setParameter("roomId", roomId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        
        List listReservations = query.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return listReservations;
    }

    @Override
    public Reservation getReservationByTimeRoom(Date startTime, Room room) {
        logger.debug("!!!!!!!ROOM ID IS: " + room.getRoomId());
        
        /* You can query using hibernate's createQuery() OR createCriteria()
         
        String matchingReservation = "FROM Reservation r WHERE r.startTime = :startTime AND r.roomId = :roomId";
        Query query = session.getCurrentSession().createQuery(matchingReservation);
        query.setParameter("startTime", startTime);
        query.setParameter("room", room);
        */
        Criteria criteria = session.getCurrentSession().createCriteria(Reservation.class);
        criteria.add(Restrictions.eq("room", room)); //IMPORTANT! when querying another entity, use the ENTITY NAME, not the column name
        criteria.add(Restrictions.eq("startTime", startTime));
        
        List listReservations = criteria.list();
        if(listReservations.size() == 0){
            return null;
        }
        
        return (Reservation)listReservations.get(0);
    }
    
}
