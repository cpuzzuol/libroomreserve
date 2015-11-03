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
public class ReservationExistsException extends RuntimeException{
  public ReservationExistsException(String message, Throwable cause) {
      super(message, cause);
  }

  public ReservationExistsException(String message) {
      super(message);
  }

  public ReservationExistsException() {
      super();
  }
}
