package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.pr.models.Product;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}