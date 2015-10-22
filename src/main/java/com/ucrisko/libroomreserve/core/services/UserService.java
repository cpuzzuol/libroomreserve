/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.services;

import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.core.services.utilities.UserList;
import java.util.List;

public interface UserService {
  public User addUser(User user);
  public User editUser(User user);
  public User deleteUser(Long userId);
  
  public User getUserById(Long userId);
  
  public User getUserByUserName(String userName);
  
  public UserList getAllUsers();
}
