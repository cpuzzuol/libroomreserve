/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.resources.asm;

import com.ucrisko.libroomreserve.core.services.utilities.RoomList;
import com.ucrisko.libroomreserve.rest.controllers.RoomController;
import com.ucrisko.libroomreserve.rest.resources.RoomListResource;
import com.ucrisko.libroomreserve.rest.resources.RoomResource;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class RoomListResourceAsm extends ResourceAssemblerSupport<RoomList, RoomListResource>{
    
    public RoomListResourceAsm(){
        super(RoomController.class, RoomListResource.class);
    }

    @Override
    public RoomListResource toResource(RoomList rooms) {
        List<RoomResource> roomResources = new RoomResourceAsm().toResources(rooms.getRooms());
        RoomListResource roomList = new RoomListResource();
        roomList.setRoomResources(roomResources);
        return roomList;
    }
    
}
