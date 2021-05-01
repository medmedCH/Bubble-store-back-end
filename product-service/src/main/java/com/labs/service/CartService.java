package com.labs.service;

import com.labs.dto.CartDto;
import com.labs.entities.Cart;
import com.labs.entities.enums.CartStatus;
import com.labs.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@Transactional
public class CartService {
    @Inject
    CartRepository cartRepository;

    public List<CartDto> findAll() {
        log.debug("Request to get all Carts");
        return this.cartRepository.findAll()
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }
    public List<CartDto> findAllActiveCarts() {
        return this.cartRepository.findByStatus(CartStatus.NEW)
                .stream()
                .map(CartService::mapToDto)
                .collect(Collectors.toList());
    }
    public Cart create(String user_id) {
        if (this.getActiveCart(user_id) == null) {
            var cart = new Cart(user_id, CartStatus.NEW);
            return this.cartRepository.save(cart);
        } else {
            throw new IllegalStateException("There is already an active cart");
        }
    }
    public CartDto createDto(String user_id) {
        return mapToDto(this.create(user_id));
    }
    public CartDto findById(Long id) {
        log.debug("Request to get Cart : {}", id);
        return this.cartRepository.findById(id).map(CartService::mapToDto).orElse(null);
    }
    public void delete(Long id) {
        log.debug("Request to delete Cart : {}", id);
        Cart cart = this.cartRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Cannot find cart with id "
                        + id));
        cart.setStatus(CartStatus.CANCELED);
        this.cartRepository.save(cart);
    }
    public CartDto getActiveCart(String user_id) {
        List<Cart> carts = this.cartRepository
.findByStatusAnduser_id(CartStatus.NEW, user_id);
        if (carts != null) {
            if (carts.size() == 1) {
                return mapToDto(carts.get(0));
            }
            if (carts.size() > 1) {
                throw new IllegalStateException("Many active carts detected !!!");
            }
        }
        return null;
    }
    public static CartDto mapToDto(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getUser_id(),
                cart.getStatus().name()
        );
    }
}