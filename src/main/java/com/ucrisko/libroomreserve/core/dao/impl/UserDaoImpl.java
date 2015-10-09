/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.core.dao.impl;

import com.ucrisko.libroomreserve.core.dao.UserDao;
import com.ucrisko.libroomreserve.core.entities.User;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{
  
  @Autowired
  private SessionFactory session;

  @Override
  public void addUser(User user) {
    session.getCurrentSession().save(user);
  }

  @Override
  public void editUser(User user) {
    session.getCurrentSession().update(user);
  }

  @Override
  public void deleteUser(Long userId) {
    session.getCurrentSession().delete(getUser(userId));
  }

  @Override
  public User getUser(Long userId) {
    return (User)session.getCurrentSession().get(User.class, userId);
  }

  @Override
  public List getAllUsers() {
    return session.getCurrentSession().createQuery("from User").list();
  }

  @Override
  public void deleteUsers(List<Long> userIds) {
    //session.getCurrentSession().delete(userIds);
      userIds.stream().forEach((userId) -> session.getCurrentSession().delete(getUser(userId)));
  }

}