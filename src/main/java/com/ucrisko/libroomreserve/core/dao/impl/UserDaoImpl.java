/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.dao.impl;

import com.ucrisko.libroomreserve.core.dao.UserDao;
import com.ucrisko.libroomreserve.core.entities.User;
import com.ucrisko.libroomreserve.core.services.exceptions.UserExistsException;
import com.ucrisko.libroomreserve.rest.controllers.UserController;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

  @Autowired
  private SessionFactory session;

  @Override
  public User addUser(User user) {
    session.getCurrentSession().save(user);
    return user;
  }

  @Override
  public User editUser(User user) {
    session.getCurrentSession().update(user);
    return user;
  }

  @Override
  public void deleteUser(Long userId) {
    session.getCurrentSession().delete(getUserById(userId));
  }

  @Override
  public User getUserById(Long userId) {
    return (User)session.getCurrentSession().get(User.class, userId);
  }
  
  @Override
  public User getUserByUserName(String userName){
      String matchingUserQuery = "FROM User u WHERE u.userName = :userName";
      Query query = session.getCurrentSession().createQuery(matchingUserQuery);
      query.setParameter("userName", userName);
      
      List users = query.list();
      if(users.size() == 0){
          return null;
      } 
      return (User)users.get(0);
      
  }

  @Override
  public List getAllUsers() {
    return session.getCurrentSession().createQuery("from User").list();
  }
  
}
