/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ucrisko.libroomreserve.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.ucrisko.*"})
public class WebAppConfig extends WebMvcConfigurerAdapter{

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/app/**").addResourceLocations("/app/build/");
  }
 
  
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
    configurer.enable();
  }
  
  //Jackson JSON Beans
  @Bean
  public MappingJackson2HttpMessageConverter jsonConverter(){
    MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
    jacksonConverter.setSupportedMediaTypes(Arrays.asList(MediaType.valueOf("application/json")));
    jacksonConverter.setObjectMapper(jacksonObjectMapper());
    return jacksonConverter;
  }
  
  @Bean
  public ObjectMapper jacksonObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(Include.NON_NULL);
    return objectMapper;
  }
  
  /*
  @Bean
  public InternalResourceViewResolver getIRVR(){
    System.out.println("Setting up view resolver...");
    InternalResourceViewResolver irvr = new InternalResourceViewResolver();

    
    irvr.setPrefix("/resources/public/");
    irvr.setSuffix(".html");
    return irvr;
  }
  */
}
