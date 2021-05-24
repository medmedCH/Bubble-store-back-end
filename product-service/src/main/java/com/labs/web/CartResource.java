package com.labs.web;

import com.labs.dto.CartDto;
import com.labs.service.CartService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/carts")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "cart", description = "All the cart methods")
public class CartResource {
    @Inject
    CartService cartService;
    @GET
    public List<CartDto> findAll() {
        return this.cartService.findAll();
    }
    @GET @Path("/active")
    public List<CartDto> findAllActiveCarts() {
        return this.cartService.findAllActiveCarts();
    }
    @GET @Path("/customeractive/{id}")
    public CartDto getActiveCartForCustomer(@PathParam("id") String user_id) {
        return this.cartService.getActiveCart(user_id);
    }
    @GET @Path("/exist/{id}")
    public Boolean existt(@PathParam("id") String id) {
        return this.cartService.existcart(id);
    }
    @GET @Path("/existcart/{id}")
    public CartDto existtt(@PathParam("id") String id) {
        return this.cartService.getcartuserlogin(id);
    }
    @GET @Path("/{id}")
    public CartDto findById(@PathParam("id") Long id) {
        return this.cartService.findById(id);
    }
    @POST
    @Path("/customer/{id}")
    public CartDto create(@PathParam("id") String user_id) {
        return this.cartService.createDto(user_id);
    }
    @DELETE @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.cartService.delete(id);
    }
}