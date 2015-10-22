/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.services;

import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.core.services.utilities.RoomList;
import java.util.List;

public interface RoomService {
  public Room addRoom(Room room);
  public Room editRoom(Room room);
  public Room deleteRoom(Long roomId);
  
  public Room getRoomById(Long roomId);
  public Room getRoomByRoomName(String roomName);
  
  public RoomList getAllRooms();
}
