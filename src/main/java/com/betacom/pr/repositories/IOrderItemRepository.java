package com.betacom.pr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.pr.models.OrderItem;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Integer> {

}