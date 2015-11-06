/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.controllers;

import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.core.services.RoomService;
import com.ucrisko.libroomreserve.core.services.exceptions.RoomExistsException;
import com.ucrisko.libroomreserve.core.services.utilities.RoomList;
import com.ucrisko.libroomreserve.rest.exceptions.BadRequestException;
import com.ucrisko.libroomreserve.rest.exceptions.ConflictException;
import com.ucrisko.libroomreserve.rest.resources.RoomListResource;
import com.ucrisko.libroomreserve.rest.resources.RoomResource;
import com.ucrisko.libroomreserve.rest.resources.asm.RoomListResourceAsm;
import com.ucrisko.libroomreserve.rest.resources.asm.RoomResourceAsm;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/room")
public class RoomController {
    
    @Autowired
    private RoomService roomService;
    private static Logger logger = Logger.getLogger(RoomController.class);
    /**
     * List all the rooms in the system unless the optional roomName is given, then just return that Room's resource
     * @param roomName
     * @return ResponseEntity<RoomResource>
     */
    
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<RoomListResource> listRooms(@RequestParam(value="roomNumber", required=false) String roomNumber){
      RoomList rooms = null;
      
      if(roomNumber == null){
          rooms = roomService.getAllRooms();
      } else {
          Room room = roomService.getRoomByRoomName(roomNumber);
          if(room == null){
              rooms = new RoomList(new ArrayList<Room>());
          } else {
              rooms = new RoomList(Arrays.asList(room));
          }
      }
   
      RoomListResource roomResource = new RoomListResourceAsm().toResource(rooms);
      return new ResponseEntity<RoomListResource>(roomResource, HttpStatus.OK);
    }
    
    @RequestMapping(value="/{roomId}", method = RequestMethod.GET)
    public ResponseEntity<RoomResource> getRoom(@PathVariable Long roomId){
      try{
        Room room = roomService.getRoomById(roomId);
        RoomResource roomResource = new RoomResourceAsm().toResource(room);
        return new ResponseEntity<RoomResource>(roomResource, HttpStatus.OK);
      } catch(NullPointerException exception) {
        throw new BadRequestException(exception);
      }
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<RoomResource> addRoom(@RequestBody RoomResource room){
        try{
            Room newRoom = roomService.addRoom(room.toRoom());
            RoomResource roomResource = new RoomResourceAsm().toResource(newRoom);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(roomResource.getLink("self").getHref()));
            return new ResponseEntity<RoomResource>(roomResource, headers, HttpStatus.CREATED);
        } catch(RoomExistsException exception) {
            throw new ConflictException(exception); 
        }
    }
    
    @RequestMapping(value="/{roomId}", method = RequestMethod.DELETE)
    public ResponseEntity<RoomResource> deleteUser(@PathVariable Long roomId){
        Room deletedRoom = roomService.deleteRoom(roomId);
        if(deletedRoom != null){
          RoomResource usr = new RoomResourceAsm().toResource(deletedRoom);
          return new ResponseEntity<RoomResource>(usr, HttpStatus.OK);
        } else {
          return new ResponseEntity<RoomResource>(HttpStatus.NOT_FOUND); 
        }
      }
}
