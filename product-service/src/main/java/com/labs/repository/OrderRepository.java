package com.labs.repository;


import com.labs.entities.Cart;
import com.labs.entities.Order;
import com.labs.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Boolean existsOrdersByCart_IdAndStatus(Long cartid,OrderStatus status);
    Order findOrderByCartIdAndStatus(Long cartid,OrderStatus status);
    Order findOrderByCartId(Long cartid);

}