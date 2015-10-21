/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
  
  @Id
  @Column
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long userId;
  
  @Column
  private String userName;
  
  @Column
  private String password;

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
  
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  
}
