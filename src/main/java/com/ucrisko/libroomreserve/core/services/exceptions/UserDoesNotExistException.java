/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.services.exceptions;

public class UserDoesNotExistException extends RuntimeException{
  public UserDoesNotExistException(){
    super();
  }
  
  public UserDoesNotExistException(String message){
    super(message);
  }
  
  public UserDoesNotExistException(String message, Throwable cause){
    super(message, cause);
  }
}
