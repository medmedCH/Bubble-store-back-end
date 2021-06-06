package com.labs.service;

import com.labs.dto.OrderItemDto;
import com.labs.entities.OrderItem;
import com.labs.repository.OrderItemRepository;
import com.labs.repository.OrderRepository;
import com.labs.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
@Transactional
public class OrderItemService {
    @Inject
    OrderItemRepository orderItemRepository;
    @Inject
    OrderRepository orderRepository;
    @Inject
    ProductRepository productRepository;
    public static OrderItemDto mapToDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId(),
                orderItem.getQuantity(),
                ProductService.mapToDto(orderItem.getProduct()),
                orderItem.getOrder().getId()
        );
    }
    public OrderItemDto findById(Long id) {
        log.debug("Request to get OrderItem : {}", id);
        return this.orderItemRepository.findById(id)
                .map(OrderItemService::mapToDto).orElse(null);
    }
    public OrderItemDto updateorderitem(Long id , BigDecimal qte){
        var orderItem=this.orderItemRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("The oderoteem does not exist!"));
        var order = orderItem.getOrder();
        order.setPrice(order.getPrice().subtract(orderItem.getProduct().getPrice().multiply(orderItem.getQuantity())));
        order.setTotalbubblecoin(order.getTotalbubblecoin().subtract((orderItem.getProduct().getBubblecoin().multiply(orderItem.getQuantity()))));
        order.setTotalarticles(order.getTotalarticles().subtract(orderItem.getQuantity()));
        this.orderRepository.save(order);
        orderItem.setQuantity(qte);
        this.orderItemRepository.save(orderItem);
        order.setPrice(order.getPrice().add((orderItem.getProduct().getPrice().multiply(qte))));
        order.setTotalbubblecoin(order.getTotalbubblecoin().add((orderItem.getProduct().getBubblecoin().multiply(qte))));
        order.setTotalarticles(order.getTotalarticles().add(qte));
        this.orderRepository.save(order);
        return mapToDto(orderItem);

    }
    public OrderItemDto create(OrderItemDto orderItemDto) {
        log.debug("Request to create OrderItem : {}", orderItemDto);
        var order =
                this.orderRepository
                        .findById(orderItemDto.getOrderId())
                        .orElseThrow(() ->
                                new IllegalStateException("The Order does not exist!"));
        var product =
                this.productRepository
                        .findById(orderItemDto.getProduct().getId())
                        .orElseThrow(() ->
                                new IllegalStateException("The Product does not exist!"));
        var orderItem=this.orderItemRepository.findOrderItemByOrderAndProduct(order,product);
        if (orderItem!=null){
            orderItem.setQuantity(orderItem.getQuantity().add(orderItemDto.getQuantity()));
            this.orderItemRepository.save(orderItem);
            order.setPrice(order.getPrice().add((orderItem.getProduct().getPrice().multiply(orderItemDto.getQuantity()))));
            order.setTotalbubblecoin(order.getTotalbubblecoin().add((orderItem.getProduct().getBubblecoin().multiply(orderItemDto.getQuantity()))));
            order.setTotalarticles(order.getTotalarticles().add(orderItemDto.getQuantity()));
            this.orderRepository.save(order);
            return mapToDto(orderItem);
        }else
         orderItem = this.orderItemRepository.save(
                new OrderItem(
                        orderItemDto.getQuantity(),
                        product,
                        order
                ));
        order.setPrice(order.getPrice().add((orderItem.getProduct().getPrice().multiply(orderItem.getQuantity()))));
        order.setTotalbubblecoin(order.getTotalbubblecoin().add((orderItem.getProduct().getBubblecoin().multiply(orderItem.getQuantity()))));
        order.setTotalarticles(order.getTotalarticles().add(orderItem.getQuantity()));
        this.orderRepository.save(order);
        return mapToDto(orderItem);
    }
    public void delete(Long id) {
        log.debug("Request to delete OrderItem : {}", id);
        var orderItem = this.orderItemRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("The OrderItem does not exist!"));
        var order = orderItem.getOrder();
        order.setPrice(order.getPrice().subtract(orderItem.getProduct().getPrice().multiply(orderItem.getQuantity())));
        order.setTotalbubblecoin(order.getTotalbubblecoin().subtract((orderItem.getProduct().getBubblecoin().multiply(orderItem.getQuantity()))));
        order.setTotalarticles(order.getTotalarticles().subtract(orderItem.getQuantity()));
        this.orderItemRepository.deleteById(id);
        this.orderRepository.save(order);
        order.getOrderItems().remove(orderItem);
    }
    public List<OrderItemDto> findByOrderId(Long id) {
        log.debug("Request to get all OrderItems of OrderId {}", id);
        return this.orderItemRepository.findAllByOrderId(id)
                .stream()
                .map(OrderItemService::mapToDto)
                .collect(Collectors.toList());

    }

}