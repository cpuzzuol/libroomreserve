/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unitTestTutorials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class BlogEntryController {
  
  /*USING JACKSON TO CONVERT POJO TO JSON*/
  /*@ResponseBody = send JSON, @RequestBody = receive JSON*/
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody BlogEntry getTest(){
    BlogEntry blogEntry = new BlogEntry();
    
    blogEntry.setTitle("Test Blog Entry");
    return blogEntry;
  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST)
  public @ResponseBody BlogEntry postTest(@RequestBody BlogEntry blogEntry){
    return blogEntry;
  }
}
