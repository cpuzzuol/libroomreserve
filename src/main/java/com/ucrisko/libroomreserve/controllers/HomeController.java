/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
  
  @RequestMapping(value="/roast", method=RequestMethod.GET)
  public String homePage(Model model){
    return "index";
  }
  
  @RequestMapping(value="/", method=RequestMethod.GET)
  public String indexPage(Model model){
    return "index";
  }
  
}
