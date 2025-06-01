package org.example.onlinestore.repository;

import org.example.onlinestore.entity.ProductContainer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductContainerRepository extends JpaRepository<ProductContainer, Integer> {
}