/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.resources.asm;

import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.rest.controllers.RoomController;
import com.ucrisko.libroomreserve.rest.controllers.UserController;
import com.ucrisko.libroomreserve.rest.resources.RoomResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class RoomResourceAsm extends ResourceAssemblerSupport<Room, RoomResource> {

    public RoomResourceAsm(){
        super(RoomController.class, RoomResource.class);
    }
    
    @Override
    public RoomResource toResource(Room room) {
        RoomResource roomResource = new RoomResource();
        roomResource.setRoomId(room.getRoomId());
        roomResource.setRoomCapacity(room.getRoomCapacity());
        roomResource.setRoomDescription(room.getRoomDescription());
        roomResource.setRoomNumber(room.getRoomNumber());
        
        //HATEOAS link builder object
        Link link = linkTo(methodOn(RoomController.class).getRoom(room.getRoomId())).withSelfRel();
        roomResource.add(link.withSelfRel());
        
        return roomResource;
    }
    
}
