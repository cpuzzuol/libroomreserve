/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.dao.impl;

import com.ucrisko.libroomreserve.core.dao.RoomDao;
import com.ucrisko.libroomreserve.core.entities.Room;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDaoImpl implements RoomDao{

    @Autowired
    private SessionFactory session;
    
    @Override
    public Room addRoom(Room room) {
        session.getCurrentSession().save(room);
        return room;
    }

    @Override
    public Room editRoom(Room room) {
        session.getCurrentSession().update(room);
        return room;
    }

    @Override
    public void deleteRoom(Long roomId) {
        session.getCurrentSession().delete(getRoomById(roomId));
    }

    @Override
    public Room getRoomById(Long roomId) {  
      return (Room)session.getCurrentSession().get(Room.class, roomId);
    }

    @Override
    public Room getRoomByRoomName(String roomName) {
      //case insensitive
      String matchingRoomQuery = "FROM Room r WHERE r.roomNumber = :roomName";
      Query query = session.getCurrentSession().createQuery(matchingRoomQuery);
      query.setParameter("roomName", roomName);
      
      List rooms = query.list();
      if(rooms.size() == 0){
          return null;
      } 
      return (Room)rooms.get(0);
    }

    @Override
    public List getAllRooms() {
       return session.getCurrentSession().createQuery("from Room").list();
    }
    
}
