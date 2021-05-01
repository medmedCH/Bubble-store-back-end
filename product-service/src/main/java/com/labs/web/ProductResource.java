package com.labs.web;

import com.labs.dto.ProductDto;
import com.labs.entities.Product;
import com.labs.repository.ProductRepository;
import com.labs.service.ProductService;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/products") @Produces(MediaType.APPLICATION_JSON)
@Tag(name = "product", description = "All the product methods")

public class ProductResource {
    @Inject
    ProductRepository productRepository;
    @Inject
    ProductService productService;

    @GET
    public List<ProductDto> findAll() {
        return this.productService.findAll();
    }
    @GET @Path("/count")
    public Long countAllProducts() {
        return this.productService.countAll();
    }
    @GET @Path("/{id}")
    public ProductDto findById(@PathParam("id") Long id) {
        return this.productService.findById(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public ProductDto create(ProductDto productDto) {
        return this.productService.create(productDto);
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDto updateprd(@PathParam ("id") Long id, ProductDto newww) {
       return this.productService.updateproduct(id, newww);
    }
    @DELETE @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        this.productService.delete(id);
    }
    @GET @Path("/category/{id}")
    public List<ProductDto> findByCategoryId(@PathParam("id") Long id) {
        return this.productService.findByCategoryId(id);
    }
    @GET @Path("/count/category/{id}")
    public Long countByCategoryId(@PathParam("id") Long id) {
        return this.productService.countByCategoryId(id);
    }




}