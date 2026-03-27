package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pr.models.ShoppingCart;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{
	
	

}
