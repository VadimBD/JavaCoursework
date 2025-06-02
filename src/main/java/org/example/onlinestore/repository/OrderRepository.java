package org.example.onlinestore.repository;

import org.example.onlinestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findById(UUID id);

    @Query("SELECT MAX(o.orderNumber) FROM Order o")
    Integer findMaxOrderNumber();

}