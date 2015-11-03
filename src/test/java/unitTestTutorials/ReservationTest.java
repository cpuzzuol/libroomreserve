/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTestTutorials;

import com.ucrisko.libroomreserve.core.entities.Reservation;
import com.ucrisko.libroomreserve.core.entities.User;
import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class ReservationTest {
    private MockMvc mockMvc;
    
    @Mock
    private Reservation reservation;
    //private ReservationController reservationController;
    
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void addReservation(){
        User user = new User(100L, "chrisp", "password");
        when(reservation.getUser()).thenReturn(user);
        //assertEquals((long) reservation.getReservationId(), 43L);
        assertEquals(reservation.getUser(), user);
        
    }
}
