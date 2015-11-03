/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.controllers;

import com.ucrisko.libroomreserve.core.entities.Reservation;
import com.ucrisko.libroomreserve.core.services.ReservationService;
import com.ucrisko.libroomreserve.core.services.exceptions.ReservationExistsException;
import com.ucrisko.libroomreserve.rest.exceptions.ConflictException;
import com.ucrisko.libroomreserve.rest.resources.ReservationResource;
import com.ucrisko.libroomreserve.rest.resources.asm.ReservationResourceAsm;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/reservation")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<ReservationResource> addReservation(@RequestBody ReservationResource reservation){
        try{
            Reservation newReservation = reservationService.addReservation(reservation.toReservation());
            ReservationResource reservationResource = new ReservationResourceAsm().toResource(newReservation);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(reservationResource.getLink("self").getHref()));

            return new ResponseEntity<ReservationResource>(reservationResource, headers, HttpStatus.CREATED);
        } catch (ReservationExistsException exception) {
            throw new ConflictException(exception); 
        }
    }
}
