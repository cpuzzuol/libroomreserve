/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.ucrisko.*"})
public class WebAppConfig extends WebMvcConfigurerAdapter{
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/resources/");
      //registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
 
  
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
    configurer.enable();
  }
 
  
  @Bean
  public InternalResourceViewResolver getIRVR(){
    System.out.println("Setting up view resolver...");
    InternalResourceViewResolver irvr = new InternalResourceViewResolver();
    
    irvr.setPrefix("/WEB-INF/views/");
    irvr.setSuffix(".jsp");
    irvr.setViewClass(org.springframework.web.servlet.view.JstlView.class);
    return irvr;
  }
}
