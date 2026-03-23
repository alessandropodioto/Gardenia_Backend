package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pr.models.ShoppingCart;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{
	
	

}
