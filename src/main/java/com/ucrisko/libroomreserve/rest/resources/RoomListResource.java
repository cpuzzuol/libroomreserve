/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.resources;

import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

public class RoomListResource extends ResourceSupport{
    private List<RoomResource> roomResrouces = new ArrayList<RoomResource>();

    public List<RoomResource> getRoomResrouces() {
        return roomResrouces;
    }

    public void setRoomResrouces(List<RoomResource> roomResrouces) {
        this.roomResrouces = roomResrouces;
    }
    
    
}
