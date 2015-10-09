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
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
  
  @Mock
  private UserService userService;
  
  @InjectMocks
  private UserController controller;
  
  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    
    //build the mock mvc setup
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }
  
  @Test
  public void test() throws Exception{
    User firstUser = new User(1L, "billybob");
    User secondUser = new User(2L, "buckybob");
    
    List<User> users = new ArrayList<>();
    users.add(firstUser);
    users.add(secondUser);
    
    //when(controller.listUsers(map)).thenReturn(Arrays.asList(firstUser, secondUser));
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
}
