package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.betacom.pr.models.ShoppingCart;

import java.util.List;

@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{
	
	// Find all cart items for a specific order
	List<ShoppingCart> findAllByUserOrder_Id(Integer userOrderId);
	
	// Find all cart items for a user's pending order (active cart)
	@Query("SELECT sc FROM ShoppingCart sc WHERE sc.userOrder.user.userName = ?1 AND sc.userOrder.status = 'PENDING'")
	List<ShoppingCart> findActiveCartByUser(String userName);

}
