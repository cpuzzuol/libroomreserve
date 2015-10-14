/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ucrisko.libroomreserve.core.entities.User;
import org.springframework.hateoas.ResourceSupport;

/**
 * These resource classes provide the "external representation an entity" (in this case, a User)
 * 
 * Spring HATEOAS library: adds informational links to the JSON returned by the controller and decouples the client from having any underlying knowledge of the server's naming scheme
 *                         i.e., the REST API link could change on the server side without breaking the client's configuration
 * 
 * The resource class will be responsible for converting back and forth between POJOs and JSON via Jackson (previously done directly in the controller)
 * 
 * ResourceSupport is a HATEOAS class
 */
public class UserResource extends ResourceSupport {
  //copied from User Entity
  private Long userId;
  @JsonProperty
  private String userName;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public User toUser(){
    User user = new User();
    user.setUserId(userId);
    user.setUserName(userName);
    
    return user;
  }
}
