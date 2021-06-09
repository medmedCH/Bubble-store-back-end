package com.labs.repository;


import com.labs.entities.Order;
import com.labs.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Boolean existsOrdersByCart_IdAndStatus(Long cartid,OrderStatus status);
    Order findOrderByCartIdAndStatus(Long cartid,OrderStatus status);
    Order findOrderByCartId(Long cartid);
    Order findOrdersByCartIdAndStatus(Long cartid, OrderStatus status);

}