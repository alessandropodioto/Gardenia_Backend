package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.pr.models.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {
}