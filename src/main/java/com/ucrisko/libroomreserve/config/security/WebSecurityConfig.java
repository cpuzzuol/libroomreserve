/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.config.security;

import javax.sql.DataSource;
import javax.ws.rs.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private DataSource dataSource;
  
  @Autowired
  private AuthenticationFailure authFailure;
  
  @Autowired
  private AuthenticationSuccess authSuccess;
  
  @Autowired
  private EntryPointUnauthorizedHandler unauthorizedHandler;
  
  @Autowired
  private UserDetailServiceImpl userDetails;
    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {  
      http
            .authorizeRequests()
                //.antMatchers(HttpMethod.GET, "/users").authenticated()
                .anyRequest().permitAll()
                .and()  
                .formLogin()
                    .loginPage("/signin")
                    .successHandler(authSuccess)
                    .failureHandler(authFailure)
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository());
    }
    */
  
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        /*
        http
                .csrf().disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                .and()
                //.formLogin()
                   // .successHandler(authSuccess) //sets status to 200 OK
                   // .failureHandler(authFailure) //sets status to 401 Unauthorized
                //.and()
                .authorizeRequests()
                    .antMatchers("/**")
                        .permitAll()
                .and()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository());
        */
        http
                .csrf().disable()
                //.exceptionHandling()
                //    .authenticationEntryPoint(unauthorizedHandler)
                //.and()
                .formLogin()
                    .successHandler(authSuccess) //sets status to 200 OK
                    .failureHandler(authFailure) //sets status to 401 Unauthorized
                .and()
                .authorizeRequests()
                    .antMatchers("/**")
                        .permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("cpuzzuol").password("epthnw2y").roles("ADMIN", "USER");
        */   
        auth
                .userDetailsService(userDetails);
    }
    
    private CsrfTokenRepository csrfTokenRepository() {
      HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
      repository.setHeaderName("X-XSRF-TOKEN");
      return repository;
    }
}
