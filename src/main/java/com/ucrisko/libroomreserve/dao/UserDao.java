/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.dao;

import com.ucrisko.libroomreserve.models.User;
import java.util.List;
import org.springframework.stereotype.Component;

public interface UserDao {
  public void openCurrentSession();
  public void openCurrentSessionWithTransaction();
  public void closeCurrentSession();
  public void closeCurrentSessionWithTransaction();        
  public void addUser(User user);
  public void editUser(User user);
  public void deleteUser(int userId);
  
  public User getUser(int userId);
  
  public List getAllUsers();
}
