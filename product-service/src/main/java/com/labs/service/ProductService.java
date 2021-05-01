package com.labs.service;



import com.labs.dto.ProductDto;
import com.labs.entities.Product;
import com.labs.repository.CategoryRepository;
import com.labs.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@Transactional
public class ProductService {
    @Inject
    ProductRepository productRepository;
    @Inject
    CategoryRepository categoryRepository;
    public List<ProductDto> findAll() {
        log.debug("Request to get all Products");
        return this.productRepository.findAll()
                .stream().map(ProductService::mapToDto)
                .collect(Collectors.toList());
    }
    public ProductDto findById(Long id) {
        log.debug("Request to get Product : {}", id);
        return this.productRepository.findById(id)
                .map(ProductService::mapToDto).orElse(null);
    }
    public Long countAll() {
        return this.productRepository.count();
    }
    public Long countByCategoryId(Long id) {
        return this.productRepository.countAllByCategoryId(id);
    }
    public ProductDto create(ProductDto productDto) {
        log.debug("Request to create Product : {}", productDto);
        return mapToDto(this.productRepository.save(
               new Product(
                        productDto.getTitle(),
                        productDto.getDescription(),
                        productDto.getPrice(),
                        productDto.getQuantity(),
                        categoryRepository.findById(productDto.getCategoryId())
                                .orElse(null)
                )));
    }
    public ProductDto updateproduct(Long id ,ProductDto productDto) {
        var product =
                this.productRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new IllegalStateException("The Product does not exist!"));


   product.setTitle(productDto.getTitle());
   product.setDescription(productDto.getDescription());
   product.setQuantity(productDto.getQuantity());
   product.setPrice(productDto.getPrice());
   product.setCategory(categoryRepository.findById(productDto.getCategoryId())
           .orElse(null));
   this.productRepository.save(product);
   return mapToDto(product);

    }


    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        this.productRepository.deleteById(id);
    }
    public List<ProductDto> findByCategoryId(Long id) {
        return this.productRepository.findByCategoryId(id).stream()
                .map(ProductService::mapToDto).collect(Collectors.toList());
    }
    public static ProductDto mapToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getId()
        );
    }


}