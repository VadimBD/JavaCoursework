package org.example.onlinestore.repository;

import org.example.onlinestore.entity.CartLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartLineRepository extends JpaRepository<CartLine, Integer> {
}