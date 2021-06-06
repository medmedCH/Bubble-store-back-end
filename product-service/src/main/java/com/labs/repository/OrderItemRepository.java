package com.labs.repository;


import com.labs.entities.Order;
import com.labs.entities.OrderItem;
import com.labs.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findAllByOrderId(Long id);
    OrderItem findOrderItemByOrderAndProduct(Order or, Product pr);

}