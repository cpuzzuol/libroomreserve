package com.ucrisko.libroomreserve.controllers;

import com.ucrisko.libroomreserve.models.User;
import com.ucrisko.libroomreserve.services.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.GET)
  public List<User> listUsers(Map<String, Object> map){
    User user = new User();
    map.put("user", user);
    map.put("userList", userService.getAllUsers());
    return userService.getAllUsers();
  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST)
  public void addUser(@RequestBody String userName){
    User newUser = new User();
    newUser.setUserName(userName);
    userService.addUser(newUser);
  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value="/{userId}", method = RequestMethod.PUT)
  public void editUser(@PathVariable Long userId, @RequestBody User user){
    userService.editUser(user);
  }
  
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.DELETE)
  public void deleteUsers(@RequestParam(value="userId", required=true) List<Long> userIds){
    userService.deleteUsers(userIds);
  }
}
