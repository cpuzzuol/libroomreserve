/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.resources;

import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

public class UserListResource extends ResourceSupport{
    private List<UserResource> userResources = new ArrayList<UserResource>();

    public List<UserResource> getUserResources() {
        return userResources;
    }

    public void setUserResources(List<UserResource> userResources) {
        this.userResources = userResources;
    }
}
