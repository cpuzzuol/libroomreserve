/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.controllers;

import com.ucrisko.libroomreserve.core.entities.Room;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/rooms")
public class RoomController {
    /*
    @Autowired
    private RoomService roomService;
    */
    
    /**
     * List all the rooms in the system unless the optional roomName is given, then just return that Room's resource
     * @param roomName
     * @return ResponseEntity<RoomResource>
     */
    /*
    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<RoomListResource> listRooms(@RequestParam(value="roomName", required=false) String roomName){
      RoomList rooms = null;
      
      if(roomName == null){
          rooms = roomService.getAllRooms();
      } else {
          Room room = roomService.getRoomByRoomName(roomName);
          if(room == null){
              rooms = new RoomList(new ArrayList<Room>());
          } else {
              rooms = new RoomList(Arrays.asList(room));
          }
      }
   
      RoomListResource roomResource = new RoomResourceListAsm().toResource(rooms);
      return new ResponseEntity<RoomListResource>(roomResource, HttpStatus.OK);
    }
    */
}
