/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ucrisko.libroomreserve.core.entities.Room;
import org.springframework.hateoas.ResourceSupport;

public class RoomResource extends ResourceSupport{
    private Long roomId;
    private String roomNumber;
    private String roomDescription;
    private Integer roomCapacity;

    @JsonIgnore
    public Long getRoomId() {
        return roomId;
    }
    
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
    
    @JsonProperty
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomDescription() {
        return roomDescription;
    }
    
    @JsonProperty
    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }
    
    @JsonProperty
    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
    
    public Room toRoom(){
        Room room = new Room();
        room.setRoomCapacity(roomCapacity);
        room.setRoomDescription(roomDescription);
        room.setRoomNumber(roomNumber);
        
        return room;
    }
}
