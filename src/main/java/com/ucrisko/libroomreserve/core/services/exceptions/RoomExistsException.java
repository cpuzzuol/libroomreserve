/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.core.services.exceptions;

/**
 *
 * @author cpuzzuol
 */
public class RoomExistsException extends RuntimeException{
  public RoomExistsException(String message, Throwable cause) {
      super(message, cause);
  }

  public RoomExistsException(String message) {
      super(message);
  }

  public RoomExistsException() {
      super();
  }
}
