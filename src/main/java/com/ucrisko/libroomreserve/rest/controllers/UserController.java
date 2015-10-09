package com.ucrisko.libroomreserve.rest.controllers;

import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.core.services.UserService;
import com.ucrisko.libroomreserve.rest.resources.UserResource;
import com.ucrisko.libroomreserve.rest.resources.asm.UserResourceAsm;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * On method declarations use either
 * 1) ResponseEntity<T>, which is meant to represent the entire HTTP response. 
 *     You can control anything that goes into it: status code, headers, and body.
 * - OR -
 * 
 * 2) @ResponseBody is a marker for the HTTP response body and @ResponseStatus declares the status code of the HTTP response.
 * 
 * @ResponseStatus isn't very flexible. It marks the entire method so you have to be sure that your handler method will always behave the same way. 
 * And you still can't set the headers. You'd need the HttpServletResponse or a HttpHeaders parameter.
 * Basically, ResponseEntity lets you do more.
 * 
 * http://stackoverflow.com/questions/26549379/when-use-responseentityt-and-restcontroller-for-spring-restful-applications
*/


@RestController
@RequestMapping(value="/api/user")
public class UserController {
  
  @Autowired
  private UserService userService;
  
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<User>> listUsers(Map<String, Object> map){
    User user = new User();
    map.put("user", user);
    map.put("userList", userService.getAllUsers());
    return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
  }
  
  @RequestMapping(value="/{userId}", method = RequestMethod.GET)
  public ResponseEntity<UserResource> getUser(@PathVariable Long userId){
    User user = userService.getUser(userId);
    UserResource userResource = new UserResourceAsm().toResource(user);
    
    return new ResponseEntity<UserResource>(userResource, HttpStatus.OK);
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
