/*
 * A utility class for listing all registered users in the room reservation system.
 */

package com.ucrisko.libroomreserve.core.services.utilities;

import java.util.ArrayList;
import java.util.List;
import com.ucrisko.libroomreserve.core.entities.User;
/**
 *
 * @author Chris Puzzuoli <cpuzzuol@gmail.com>
 */
public class UserList {
    
    private List<User> users = new ArrayList<User>();
    
    public UserList(List<User> users){
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
}
