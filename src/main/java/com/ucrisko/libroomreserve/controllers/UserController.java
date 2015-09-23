package com.ucrisko.libroomreserve.controllers;

import com.ucrisko.libroomreserve.models.User;
import com.ucrisko.libroomreserve.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @RequestMapping(value="/user", method=RequestMethod.GET)
  public String setupForm(Map<String, Object> map){
    User user = new User();
    map.put("user", user);
    map.put("userList", userService.getAllUsers());
    return "user";
  }
  @RequestMapping(value="/user.do", method=RequestMethod.POST)
  public String doActions(@ModelAttribute User user, BindingResult result, @RequestParam String action, Map<String, Object> map){
    User userResult = new User();
    
    switch(action.toLowerCase()){ 
      case "add":
        userService.addUser(user);
        userResult = user;
        break;
      case "edit":
        userService.editUser(user);
        userResult = user;
        break;
      case "delete":
        userService.deleteUser(user.getUserId());
        userResult = user;
        System.out.println("You're a towel");
        break;
      case "search":
        User searchedUser = userService.getUser(user.getUserId());
        userResult = searchedUser != null ? searchedUser : new User();
        break;
    }
    map.put("user", userResult);
    map.put("userList", userService.getAllUsers());
    
    return "user";
  }
  
  @RequestMapping(value="/user/{id}", method=RequestMethod.GET)
  public String userId(Model model, @PathVariable(value="id") int id){
    User userResult = new User();
    
    User searchedUser = userService.getUser(id);
    if(userResult != null){
      userResult = searchedUser;
      model.addAttribute("user", userResult);
    }
    return "deleteUser";
  }
}
