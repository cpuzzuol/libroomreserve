/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.services;

import com.ucrisko.libroomreserve.core.entities.User;
import java.util.List;

public interface UserService {
  public User addUser(User user);
  public User editUser(Long userId, User user);
  public User deleteUser(Long userId);
  
  public User getUser(Long userId);
  
  public List getAllUsers();
}
