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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class User {
  
  private Long userId;
  private String userName;
  private String password;
  private List<Reservation> reservations = new ArrayList<Reservation>();

  public User(){}
  public User(Long userId, String userName, String password){
      this.userId = userId;
      this.userName = userName;
      this.password = password;
  }
  
  @Id
  @Column
  @GeneratedValue(strategy=GenerationType.AUTO)
  public Long getUserId() {
    return userId;
  }
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(unique=true, nullable=false)
  public String getUserName() {
    return userName;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  @Column(nullable=false)
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }

  @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }
}
