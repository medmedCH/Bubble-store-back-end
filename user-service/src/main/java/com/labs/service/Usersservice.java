/*
package com.labs.service;

import com.labs.entities.User;
import com.labs.utils.Keycloakconfig;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.ArrayList;
import java.util.*;

public class Usersservice {

    public List<User> getAllUsers()
    {
        RoleResource roleResource =  Keycloakconfig.getInstance()
                .realm("bubble")
                .roles()
                .get("user");
        Set<UserRepresentation> userRepresentationList =   roleResource.getRoleUserMembers();


        List<User> userList = new ArrayList<>();

        userRepresentationList.forEach(userRepresentation -> {
            User us = new User();
            us.setId(userRepresentation.getId());
            us.setEmail(userRepresentation.getEmail());
            us.setUsername(userRepresentation.getUsername());
            us.setImg(userRepresentation.getAttributes().get("img").get(0));
            us.setRole("user");
            us.setEnabled(true);
            userList.add(us);
        });

        return userList;

    }

}
*/
