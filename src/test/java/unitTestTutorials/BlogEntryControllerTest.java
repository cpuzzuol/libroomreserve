/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitTestTutorials;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Chris Puzzuoli <cpuzzuol@gmail.com>
 */
public class BlogEntryControllerTest {
  private MockMvc mockMvc;
  
  @InjectMocks
  private BlogEntryController controller;
  
  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }
  
  @Test
  public void test() throws Exception{
    //request builder method chaining
   /* mockMvc.perform(post("/test")
            .content("{\"title\":\"Test Blog Title\"}")
            .contentType(MediaType.APPLICATION_JSON)
    )
            .andExpect(jsonPath("$.title", is("Test Blog Title")))
            .andDo(print());
           */
    mockMvc.perform(get("/test")).andDo(print());
  }
}
