/*
package com.labs.utils;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class Keycloakconfig {
    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:9080/auth";
    final static String realm = "master";
    final static String clientId = "shopback";
    final static String userName = "bubble-admin";
    final static String password = "123";



    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(userName)
                    .password(password)
                    .clientId(clientId)
                    .build();


        }
        return keycloak;
    }
}
*/
