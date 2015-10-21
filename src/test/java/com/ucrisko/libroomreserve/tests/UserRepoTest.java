/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.tests;

import com.ucrisko.libroomreserve.config.WebAppConfig;
import com.ucrisko.libroomreserve.core.dao.UserDao;
import com.ucrisko.libroomreserve.core.dao.impl.UserDaoImpl;
import com.ucrisko.libroomreserve.core.entities.User;
import java.util.Properties;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Helpful Links:
 * https://spring.io/blog/2011/06/21/spring-3-1-m2-testing-with-configuration-classes-and-profiles
 * http://stackoverflow.com/questions/23999043/failed-to-load-applicationcontext-in-unittests-no-xml-spring
*/
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class UserRepoTest {
  
  /*
  @WebAppConfiguration //IN PREVIOUS VERSIONS OF SPRING THIS WAS JUST @Configuration
  @EnableTransactionManagement
  @ComponentScan({ "com.ucrisko.*" })
  @PropertySource(value = { "classpath:application.properties" })
  static class ContextConfiguration{
    //this bean will be injected into the main class
    @Bean
    public UserDao userDaoBean(){
      UserDao userDao = new UserDaoImpl();
      return userDao;
    }
    
    @Autowired
    private Environment environment;
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.ucrisko.libroomreserve.core.entities" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
   
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
     
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;        
    }
   
  }
  
  @Autowired
  private UserDao userDao;
  private User user;
  
  @Before
  @Transactional
  @Rollback(false) //allow persist functions to be accessible
  public void setup(){
    user = new User();
    user.setUserId(100L);
    user.setUserName("Chris Puzzuoli");
    
    //userDao.addUser(user);
  }
  
  @Test
  @Transactional
  public void test(){
    //assertNotNull(userDao.getUser(1L));
  }
  
  */
}
