/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.tests;

import com.ucrisko.libroomreserve.core.entities.Room;
import com.ucrisko.libroomreserve.core.services.RoomService;
import com.ucrisko.libroomreserve.core.services.utilities.RoomList;
import com.ucrisko.libroomreserve.rest.controllers.RoomController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author cpuzzuol
 */
public class RoomControllerTest {
    
    private MockMvc mockMvc; 
    
    @Mock
    private RoomService roomService;
  
    @InjectMocks
    private RoomController controller;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    
        //build the mock mvc setup
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @Test
    public void createRoom() throws Exception{
        Room firstRoom = new Room();
            firstRoom.setRoomCapacity(20);
            firstRoom.setRoomDescription("My office");
            firstRoom.setRoomId(1L);
            firstRoom.setRoomNumber("G01T");
            
        Room secondRoom = new Room();
            secondRoom.setRoomCapacity(50);
            secondRoom.setRoomDescription("Your office");
            secondRoom.setRoomId(2L);
            secondRoom.setRoomNumber("G01S");    

        List rooms = new ArrayList<>();
        rooms.add(firstRoom);
        rooms.add(secondRoom);
        
        RoomList roomList = new RoomList(rooms);

        when(roomService.getAllRooms()).thenReturn(roomList);

        //run mock mvc environment
        mockMvc.perform(get("/api/room"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
                //.andExpect(jsonPath("$", hasSize(1)))
                //.andExpect(jsonPath("$.roomResources.roomId", is(1)))
                //.andExpect(jsonPath("$.roomResources[*].roomCapacity", is(20)))
                //.andExpect(jsonPath("$.roomResources[*].roomNumber", is("G01T")))
                //.andExpect(jsonPath("$.roomResources[*].roomDescription", is("My office")))
                .andDo(print());
    }
}
