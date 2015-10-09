/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.services.impl;

import com.ucrisko.libroomreserve.core.dao.UserDao;
import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.core.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserDao userDao;
  
  @Transactional
  public void addUser(User user) {
    userDao.addUser(user);
  }

  @Transactional
  public void editUser(User user) {
    userDao.editUser(user);
  }

  @Transactional
  public void deleteUser(Long userId) {
    userDao.deleteUser(userId);
  }

  @Transactional
  public User getUser(Long userId) {
    return userDao.getUser(userId);
  }

  @Transactional
  public List getAllUsers() {
    return userDao.getAllUsers();
  }

  @Transactional
  public void deleteUsers(List<Long> userIds) {
    userDao.deleteUsers(userIds);
  }

}
