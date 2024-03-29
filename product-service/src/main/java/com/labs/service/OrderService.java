package com.labs.service;

import com.labs.dto.OrderDto;
import com.labs.dto.OrderItemDto;
import com.labs.entities.Cart;
import com.labs.entities.Order;
import com.labs.entities.enums.CartStatus;
import com.labs.entities.enums.OrderStatus;
import com.labs.repository.CartRepository;
import com.labs.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@Transactional
public class OrderService {
    @Inject
    OrderRepository orderRepository;
    @Inject
    CartRepository cartRepository;
    public List<OrderDto> findAll() {
        log.debug("Request to get all Orders");
        return this.orderRepository.findAll().stream().map(OrderService::mapToDto)
                .collect(Collectors.toList());
    }
    public OrderDto findById(Long id) {
        log.debug("Request to get Order : {}", id);
        return this.orderRepository.findById(id)
                .map(OrderService::mapToDto).orElse(null);
    }
    public OrderDto create(OrderDto orderDto) {
        log.debug("Request to create Order : {}", orderDto);
        Long cartId = orderDto.getCart().getId();
        if(!this.existsOrderByCart(cartId)){
        Cart cart = this.cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalStateException(
                        "The Cart with ID[" + cartId + "] was not found !"));

        return mapToDto(this.orderRepository.save(new Order(BigDecimal.ZERO,BigDecimal.ZERO,BigDecimal.ZERO,
                OrderStatus.CREATION,
                Collections.emptySet(), cart)));}
        else {
            throw new IllegalStateException("There is already an order in this cart");

        }
    }
    @Transactional
    public void delete(Long id) {
        log.debug("Request to delete Order : {}", id);
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Order with ID[" + id + "] cannot be found!"));
        orderRepository.delete(order);
    }
    public boolean existsOrderByCart(Long id) {
        return this.orderRepository.existsOrdersByCart_IdAndStatus(id,OrderStatus.CREATION);
    }
    public OrderDto getorders(Long cartid){
        Order order= this.orderRepository.findOrdersByCartIdAndStatus(cartid,OrderStatus.CLOSED);
        return  mapToDto(order);
    }
   public OrderDto getuserorder(Long cartid){
       Order order= this.orderRepository.findOrderByCartId(cartid);
        return  mapToDto(order);
   }

    public static OrderDto mapToDto(Order order) {
        Set<OrderItemDto> orderItems = order.getOrderItems()
                .stream().map(OrderItemService::mapToDto).collect(Collectors.toSet());
        return new OrderDto(
                order.getId(),
                order.getPrice(),
                order.getTotalbubblecoin(),
                order.getTotalarticles(),
                order.getStatus().name(),
                orderItems,
                CartService.mapToDto(order.getCart())
        );
    }
}