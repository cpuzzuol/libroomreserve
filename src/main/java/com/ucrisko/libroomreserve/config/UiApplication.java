/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.config;

import java.security.Principal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UiApplication {
  @RequestMapping(value="/user")
  public Principal user(Principal user) {
    return user;
  }
}
