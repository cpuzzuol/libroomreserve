/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.dao;

import com.ucrisko.libroomreserve.core.entities.User;
import java.util.List;
import org.springframework.stereotype.Component;

public interface UserDao {
  public User addUser(User user);
  public void editUser(User user);
  public void deleteUser(Long userId);
  public void deleteUsers(List<Long> userIds);
  
  public User getUserById(Long userId);
  public User getUserByUserName(String userName);
  
  public List getAllUsers();
}
