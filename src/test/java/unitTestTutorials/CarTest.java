package unitTestTutorials;


import unitTestTutorials.Car;
import unitTestTutorials.Engine;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class CarTest {
  @Mock
  private Engine engine;
  
  @InjectMocks  //engine is INJECTED into car
  private Car car;
  
  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
  }
  
  @Test
  public void testWarning(){
    when(engine.getRpm()).thenReturn(6000);
    car.accelerate();
    
    assertEquals("Slow down, bro!", car.getWarningMessage());
  }
}
