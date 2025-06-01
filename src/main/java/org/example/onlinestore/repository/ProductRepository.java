package org.example.onlinestore.repository;

import org.example.onlinestore.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductByBrandId(int brandId);
    List<Product> findByTopSalesTrue();
    List<Product> findByNoveltyIsTrue();
    List<Product> findByTopSalesTrue(Pageable pageable);
    List<Product> findByNoveltyIsTrue(Pageable pageable);
}