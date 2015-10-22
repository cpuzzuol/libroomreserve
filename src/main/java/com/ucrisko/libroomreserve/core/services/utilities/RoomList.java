/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.services.utilities;

import com.ucrisko.libroomreserve.core.entities.Room;
import java.util.ArrayList;
import java.util.List;


public class RoomList {
    private List<Room> rooms = new ArrayList<Room>(); 
    
    public RoomList(List<Room> rooms){
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
