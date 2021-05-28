package com.labs.web;

import com.labs.dto.OrderItemDto;
import com.labs.dto.ProductDto;
import com.labs.service.OrderItemService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
@Path("/order-items")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Orderitem", description = "All the oreritem methods")
public class OrderItemResource {
    @Inject
    OrderItemService itemService;
    @GET
    @Path("/order/{id}")
    public List<OrderItemDto> findByOrderId(@PathParam("id") Long id) {
        return this.itemService.findByOrderId(id);
    }
    @GET
    @Path("/{id}")
    public OrderItemDto findById(@PathParam("id") Long id) {
        return this.itemService.findById(id);
    }
    @GET
    @Path("total/{id}")
    public int findByqtetotalbyId(@PathParam("id") Long id) {
        return this.itemService.getquantityorderitems(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderItemDto create(OrderItemDto orderItemDto) {
        return this.itemService.create(orderItemDto);
    }
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.itemService.delete(id);
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderItemDto updateorderitem(@PathParam ("id") Long id, BigDecimal qte) {
        return this.itemService.updateorderitem(id,qte);
    }
}
