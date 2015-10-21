/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucrisko.libroomreserve.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.ucrisko.libroomreserve.core.services.utilities.UserList;
import com.ucrisko.libroomreserve.rest.controllers.UserController;
import com.ucrisko.libroomreserve.rest.resources.UserListResource;
import com.ucrisko.libroomreserve.rest.resources.UserResource;
import java.util.List;

/**
 *
 * @author cpuzzuol
 */
public class UserResourceListAsm extends ResourceAssemblerSupport<UserList, UserListResource>{

    public UserResourceListAsm() {
        super(UserController.class, UserListResource.class);
    }
    
    @Override
    public UserListResource toResource(UserList userList) {
        List<UserResource> listUsers = new UserResourceAsm().toResources(userList.getUsers());
        UserListResource resource = new UserListResource();
        resource.setUserResources(listUsers);
        return resource;
    }
    
}
