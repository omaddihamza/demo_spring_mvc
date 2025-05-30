package com.me.demospringmvc.repository;

import com.me.demospringmvc.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
