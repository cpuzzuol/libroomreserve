/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.dao;

import com.ucrisko.libroomreserve.core.entities.Room;
import java.util.List;

/**
 *
 * @author cpuzzuol
 */
public interface RoomDao {
  public Room addUser(Room room);
  public Room editUser(Room room);
  public void deleteUser(Long roomId);
  
  public Room getRoomById(Long roomId);
  public Room getRoomByRoomName(String roomName);
  
  public List getAllRooms();
}
