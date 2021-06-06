package com.labs.web;


import com.labs.security.TokenService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("/user")
@Produces(MediaType.TEXT_PLAIN)
public class Controlleruser {

    @Inject
    JsonWebToken jwt;
    /*@Inject
    Usersservices usersservices;*/
    @Inject
    TokenService tokenService;

    @POST
    @Path("/access-token")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAccessToken(@QueryParam("username") String username, @QueryParam("password") String password) throws IOException, InterruptedException {
        return tokenService.getAccessToken(username, password);
    }

    @GET
    @RolesAllowed("user")
    @Path("/current/info")
    public JsonWebToken getCurrentUserInfo() {
        return jwt;
    }

    @GET()
    @Path("/current/info-alternative")
    public Principal getCurrentUserInfoAlternative(@Context SecurityContext ctx) {
        return ctx.getUserPrincipal();
    }


  /* @GET
   @Path("/admin/users")
   @Produces(MediaType.MEDIA_TYPE_WILDCARD)
    public Integer findalluserss() {
        return Keycloakconfig.getInstance().realm("bubble").users().count();
    }
*/

    @GET
    @RolesAllowed("user")
    @Path("/current/info/claims")
    public Map<String, Object> getCurrentUserInfoClaims() {
        return jwt.getClaimNames()
                .stream()
                .map(name -> Map.entry(name, jwt.getClaim(name)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue)
                );
    }
}