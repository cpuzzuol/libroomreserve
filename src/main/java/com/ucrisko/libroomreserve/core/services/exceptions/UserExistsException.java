/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserExistsException extends RuntimeException{
  public UserExistsException(String message, Throwable cause) {
      super(message, cause);
  }

  public UserExistsException(String message) {
      super(message);
  }

  public UserExistsException() {
      super();
  }
}
