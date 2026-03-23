package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.betacom.pr.models.Subcategory;

public interface ISubcategoryRepository extends JpaRepository<Subcategory, Integer> {
}