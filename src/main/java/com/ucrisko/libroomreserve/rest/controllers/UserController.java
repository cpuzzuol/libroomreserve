package com.ucrisko.libroomreserve.rest.controllers;

import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.core.services.UserService;
import com.ucrisko.libroomreserve.core.services.exceptions.UserDoesNotExistException;
import com.ucrisko.libroomreserve.core.services.exceptions.UserExistsException;
import com.ucrisko.libroomreserve.core.services.utilities.UserList;
import com.ucrisko.libroomreserve.rest.exceptions.BadRequestException;
import com.ucrisko.libroomreserve.rest.exceptions.ConflictException;
import com.ucrisko.libroomreserve.rest.resources.UserResource;
import com.ucrisko.libroomreserve.rest.resources.asm.UserResourceAsm;
import java.net.URI;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ucrisko.libroomreserve.rest.resources.asm.UserResourceListAsm;
import com.ucrisko.libroomreserve.rest.resources.UserListResource;
import java.util.ArrayList;
import java.util.Arrays;



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
  public ResponseEntity<UserListResource> getAllUsers(@RequestParam(value="userName", required= false) String userName){
      UserList users = null;
      
      if(userName == null){
          users = userService.getAllUsers();
      } else {
          User user = userService.getUserByUserName(userName);
          if(user == null){
              users = new UserList(new ArrayList<User>());
          } else {
              users = new UserList(Arrays.asList(user));
          }
      }
   
      UserListResource userResource = new UserResourceListAsm().toResource(users);
      return new ResponseEntity<UserListResource>(userResource, HttpStatus.OK);
  }
  
  @RequestMapping(value="/{userId}", method = RequestMethod.GET)
  public ResponseEntity<UserResource> getUser(@PathVariable Long userId){
    try{
      User user = userService.getUserById(userId);
      UserResource userResource = new UserResourceAsm().toResource(user);
      return new ResponseEntity<UserResource>(userResource, HttpStatus.OK);
    } catch(NullPointerException exception) {
      throw new BadRequestException(exception);
    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<UserResource> addUser(@RequestBody UserResource newAccount){
    try {
      User newUser = userService.addUser(newAccount.toUser());
      UserResource usr = new UserResourceAsm().toResource(newUser);
      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(URI.create(usr.getLink("self").getHref()));
      return new ResponseEntity<UserResource>(usr, headers, HttpStatus.CREATED);
    } catch(UserExistsException exception) {
        throw new ConflictException(exception);
    }
  }

  @RequestMapping(value="/{userId}", method = RequestMethod.PUT)
  public ResponseEntity<UserResource> editUser(@PathVariable Long userId, @RequestBody UserResource userResource){
    User updatedUser = userService.editUser(userResource.toUser());
    if(updatedUser != null){
      UserResource usr = new UserResourceAsm().toResource(updatedUser);
      return new ResponseEntity<UserResource>(usr, HttpStatus.OK);
    } else {
      return new ResponseEntity<UserResource>(HttpStatus.NOT_FOUND); 
    }
  }
  
  @RequestMapping(method = RequestMethod.DELETE)
  public ResponseEntity<UserResource> deleteUser(@RequestParam(value="userId", required=true) Long userId){
    User deletedUser = userService.deleteUser(userId);
    if(deletedUser != null){
      UserResource usr = new UserResourceAsm().toResource(deletedUser);
      return new ResponseEntity<UserResource>(usr, HttpStatus.OK);
    } else {
      return new ResponseEntity<UserResource>(HttpStatus.NOT_FOUND); 
    }
  }
}
