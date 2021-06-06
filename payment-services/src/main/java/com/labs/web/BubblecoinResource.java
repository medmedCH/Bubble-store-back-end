package com.labs.web;

import com.labs.dto.SoldesBCoinDto;
import com.labs.service.Bubblecoinservices;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@ApplicationScoped
@Path("/bubblecoin")
@Produces(MediaType.APPLICATION_JSON)
public class BubblecoinResource {
    @Inject
    Bubblecoinservices bubblecoinservices;
    @POST
    @Path("/{id}")
    public SoldesBCoinDto create(@PathParam("id") String user_id) {
        return this.bubblecoinservices.createDto(user_id);
    }
    @PUT @Path("/load/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public SoldesBCoinDto updateload(@PathParam ("id") Long id, BigDecimal balance) {
        return this.bubblecoinservices.loadaccount(id,balance);
    }
    @PUT @Path("/unload/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SoldesBCoinDto updateunload(@PathParam ("id") Long id, BigDecimal balance) {
        return this.bubblecoinservices.unloadaccount(id,balance);
    }
    @GET
    @Path("/{id}")
    public SoldesBCoinDto findById(@PathParam("id") String id) {
        return this.bubblecoinservices.getsolde(id);
    }
}