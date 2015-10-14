/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.tests;

import com.ucrisko.libroomreserve.rest.controllers.UserController;
import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.core.services.UserService;
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
 * @author Chris Puzzuoli <cpuzzuol@gmail.com>
 */
public class UserControllerTest {
  private MockMvc mockMvc;
  
  private ArgumentCaptor<User> userCaptor;
  
  @Mock
  private UserService userService;
  
  @InjectMocks
  private UserController controller;
  
  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    
    //build the mock mvc setup
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    
    userCaptor = ArgumentCaptor.forClass(User.class);
  }
  
  @Test
  public void test() throws Exception{
    User firstUser = new User(1L, "billybob");
    User secondUser = new User(2L, "buckybob");
    
    List<User> users = new ArrayList<>();
    users.add(firstUser);
    users.add(secondUser);

    when(userService.getAllUsers()).thenReturn(users);
    
    //run mock mvc environment
    mockMvc.perform(get("/api/user"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON + ";charset=UTF-8"))
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].userId", is(1)))
            .andExpect(jsonPath("$[0].userName", is("billybob")))
            .andExpect(jsonPath("$[1].userId", is(2)))
            .andExpect(jsonPath("$[1].userName", is("buckybob")))
            .andDo(print());
  }
  
  @Test
  public void testResponseEntityController(){
    User user = new User(44L, "bollybob");
    user.setUserId(44L);
    user.setUserName("bommybob");
    
    when(userService.getUser(user.getUserId())).thenReturn(user);
  }
  
  @Test
  public void testPostUser() throws Exception{
    User user = new User(1L, "tonkatruck");
    
    when(userService.addUser(any(User.class))).thenReturn(user);
    
    mockMvc.perform(post("/api/user")
            .content("{\"userId\":\"1\",\"userName\":\"tonkatruck\"}")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", is(user.getUserName())))
                .andExpect(status().isCreated())
                .andDo(print());
           
   
    //verify(userService).addUser(any(User.class));
    verify(userService).addUser(userCaptor.capture());
    
    //get the username from what was captured in the POST method and assert that it posted the correct username
    //String username = userCaptor.getValue().getUserName();
    //assertEquals("tonkatruck", username);
  }
  
}
