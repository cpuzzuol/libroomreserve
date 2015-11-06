/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.resources.asm;

import com.ucrisko.libroomreserve.core.entities.Reservation;
import com.ucrisko.libroomreserve.rest.controllers.ReservationController;
import com.ucrisko.libroomreserve.rest.resources.ReservationResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author cpuzzuol
 */
public class ReservationResourceAsm extends ResourceAssemblerSupport<Reservation, ReservationResource>{

    public ReservationResourceAsm(){
        super(ReservationController.class, ReservationResource.class);
    }
    @Override
    public ReservationResource toResource(Reservation reservation) {
        ReservationResource reservationResource = new ReservationResource();
        reservationResource.setReservationId(reservation.getReservationId());
        reservationResource.setStartTime(reservation.getStartTime());
        reservationResource.setEndTime(reservation.getEndTime());
        reservationResource.setRoom(reservation.getRoom());
        reservationResource.setUser(reservation.getUser());
        reservationResource.setNote(reservation.getNote());
        
        //HATEOAS link builder object
        //Link link = linkTo(methodOn(ReservationController.class).getReservation(reservation.getReservationId())).withSelfRel();
        //reservationResource.add(link.withSelfRel());
        
        return reservationResource;
    }
    
}
