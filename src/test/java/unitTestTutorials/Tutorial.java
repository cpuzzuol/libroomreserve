package unitTestTutorials;


import unitTestTutorials.Calculator;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris Puzzuoli <cpuzzuol@gmail.com>
 */
public class Tutorial {
  
  @Mock  //mockito
  private Calculator calc;
  
  @Before //runs before every test (part of JUNIT)
  public void setup(){
    MockitoAnnotations.initMocks(this); //same as writing "calc = new Calculator();"
  }
  
  @Test
  public void testAbs(){
    when(calc.abs(-20)).thenReturn(20);
    assertEquals(15, calc.abs(-20));
  }
  
}
