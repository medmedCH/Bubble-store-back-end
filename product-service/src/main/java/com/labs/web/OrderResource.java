package com.labs.web;

import com.labs.dto.OrderDto;
import com.labs.entities.Cart;
import com.labs.service.OrderService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "order", description = "All the order methods")
public class OrderResource {
    @Inject
    OrderService orderService;
    @GET
    public List<OrderDto> findAll() {
        return this.orderService.findAll();
    }
    @GET
    @Path("getorder/{id}")
    public OrderDto findorder(@PathParam("id") Long id) {
        return this.orderService.getuserorder(id);
    }
    @GET
    @Path("/{id}")
    public OrderDto findById(@PathParam("id") Long id) {
        return this.orderService.findById(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDto create(OrderDto orderDto) {
        return this.orderService.create(orderDto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.orderService.delete(id);
    }
    @GET
    @Path("/exists/{id}")
    public boolean existsById(@PathParam("id") Long id) {
        return this.orderService.existsOrderByCart(id);
    }
}