package org.example.onlinestore.repository;

import org.example.onlinestore.entity.Paymant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymantRepository extends JpaRepository<Paymant, Integer> {
}