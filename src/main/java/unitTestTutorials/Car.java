/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitTestTutorials;

/**
 *
 * @author Chris Puzzuoli <cpuzzuol@gmail.com>
 */
public class Car {
  private Engine engine;
  private String warningMessage;
  
  public Car(Engine engine){
    this.engine = engine;
  }

  public String getWarningMessage() {
    return warningMessage;
  }

  public void setWarningMessage(String warningMessage) {
    this.warningMessage = warningMessage;
  }
  
  public void accelerate(){
    engine.increaseRpm();
    
    if(engine.getRpm() > 5000){
      setWarningMessage("Slow down, bro!");
    }
    
  }
}
