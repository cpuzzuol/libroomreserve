/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.services.impl;

import com.ucrisko.libroomreserve.core.dao.RoomDao;
import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.core.services.RoomService;
import com.ucrisko.libroomreserve.core.services.exceptions.RoomExistsException;
import com.ucrisko.libroomreserve.core.services.utilities.RoomList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{
    //error logger
    final static Logger logger = Logger.getLogger(RoomServiceImpl.class); 
    
    @Autowired
    private RoomDao roomDao;
    
    @Override
    public Room addRoom(Room room){
        Room existingRoom = roomDao.getRoomByRoomName(room.getRoomNumber());
        if(existingRoom != null){
            logger.error("!!!!THE ROOM ALREADY EXISTS!!!!");
            throw new RoomExistsException("The room " + room.getRoomNumber() + " is already in the system. All rooms must be unique!");
        }
        Room roomToAdd = roomDao.addRoom(room);
        return roomToAdd;
    }

    @Override
    public Room editRoom(Room room) {
        roomDao.editRoom(room);
        return room;
    }

    @Override
    public Room deleteRoom(Long roomId) {
        Room deletedRoom = getRoomById(roomId);
        roomDao.deleteRoom(roomId);
        
        return deletedRoom;
    }

    @Override
    public Room getRoomById(Long roomId) {
        return roomDao.getRoomById(roomId);
    }

    @Override
    public Room getRoomByRoomName(String roomName) {
        return roomDao.getRoomByRoomName(roomName);
    }

    @Override
    public RoomList getAllRooms() {
        RoomList roomList = new RoomList(roomDao.getAllRooms());
        return roomList;
    }
    
}
