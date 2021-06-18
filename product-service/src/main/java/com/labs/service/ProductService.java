package com.labs.service;

import com.labs.dto.ProductDto;
import com.labs.entities.Product;
import com.labs.entities.enums.ProductDevise;
import com.labs.repository.CategoryRepository;
import com.labs.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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
                        productDto.getBubblecoin(),
                        productDto.getQuantity(),
                        productDto.getImgpr(),
                        productDto.getImages1(),
                       productDto.getImages2(),
                       productDto.getImages3(),
                       ProductDevise.valueOf(productDto.getDevise()),
                       categoryRepository.findById(productDto.getCategory().getId())
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
   product.setImgpr(productDto.getImgpr());
   product.setDevise(product.getDevise());
   product.setImages1(productDto.getImages1());
   product.setImages2(productDto.getImages2());
   product.setImages3(productDto.getImages3());
   product.setBubblecoin(productDto.getBubblecoin());
   product.setCategory(categoryRepository.findById(productDto.getCategory().getId())
           .orElse(null));
   this.productRepository.save(product);
   return mapToDto(product);

    }
//    public ProductDto updateproductqte(Long id ,Integer qte) {
//        var product =
//                this.productRepository
//                        .findById(id)
//                        .orElseThrow(() ->
//                                new IllegalStateException("The Product does not exist!"));
//
//        product.setQuantity(qte);
//        this.productRepository.save(product);
//        return mapToDto(product);
//
//    }

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
                product.getBubblecoin(),
                product.getQuantity(),
                product.getImgpr(),
                product.getImages1(),
                product.getImages2(),
                product.getImages3(),
                product.getDevise().name(),
                CategoryService.mapToDto(product.getCategory(), 0L)
        );
    }
}