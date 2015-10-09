/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.services;

import com.ucrisko.libroomreserve.core.entities.User;
import java.util.List;

public interface UserService {
  public void addUser(User user);
  public void editUser(User user);
  public void deleteUser(Long userId);
  public void deleteUsers(List<Long> userIds);
  
  public User getUser(Long userId);
  
  public List getAllUsers();
}
