/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.config;

import com.ucrisko.libroomreserve.models.User;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 *
 * @author Chris Puzzuoli <cpuzzuol@gmail.com>
 */
public class HibernateTest {
  public static void main(String[] args){
    Configuration config = new Configuration();
    config.addClass(User.class);
    config.configure("hibernate.cfg.xml");
    
    
  }
}
