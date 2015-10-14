/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.services.impl;

import com.ucrisko.libroomreserve.core.dao.UserDao;
import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.core.services.UserService;
import com.ucrisko.libroomreserve.core.services.exceptions.UserExistsException;
import java.util.List;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
  //error logger
  final static Logger logger = Logger.getLogger(UserServiceImpl.class);
  
  @Autowired
  private UserDao userDao;
  
  @Transactional
  public User addUser(User user) {
    User addedUser = userDao.addUser(user);
    if(addedUser == null){
      logger.error("!!!!ACCOUNT ALREADY EXISTS WITH THAT NAME!!!!");
      throw new UserExistsException("The user " + user.getUserName() + " already exists.");
    }
    return getUser(user.getUserId());
  }

  @Transactional
  public User editUser(Long userId, User user) {
    userDao.editUser(user);
    return user;
  }

  @Transactional
  public User deleteUser(Long userId) {
    User deletedUser = getUser(userId);
    userDao.deleteUser(userId);
    
    return deletedUser;
  }

  @Transactional
  public User getUser(Long userId) {
    return userDao.getUser(userId);
  }

  @Transactional
  public List getAllUsers() {
    return userDao.getAllUsers();
  }

}
