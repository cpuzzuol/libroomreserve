/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.rest.resources.asm;

import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.rest.controllers.UserController;
import com.ucrisko.libroomreserve.rest.resources.UserResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * This class assembles User resources (using HATEOAS ResourceAssemblerSupport) by converting a User into a UserResource
 */
public class UserResourceAsm extends ResourceAssemblerSupport<User, UserResource>{

  public UserResourceAsm(){
    super(UserController.class, UserResource.class);
  }
  
  //Creates a user resource
  @Override
  public UserResource toResource(User user) {
    UserResource userResource = new UserResource();
    userResource.setUserId(user.getUserId());
    userResource.setUserName(user.getUserName());
    userResource.setPassword(user.getPassword());
    
    //HATEOAS link builder object
    Link link = linkTo(methodOn(UserController.class).getUser(user.getUserId())).withSelfRel();
    userResource.add(link.withSelfRel());
    
    return userResource;
  }

}
