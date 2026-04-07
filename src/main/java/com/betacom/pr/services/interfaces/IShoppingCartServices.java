package com.betacom.pr.services.interfaces;

import java.util.List;

import com.betacom.pr.dto.inputs.ShoppingCartReq;
import com.betacom.pr.dto.outputs.ShoppingCartDTO;


public interface IShoppingCartServices {
	
	void create(ShoppingCartReq req) throws Exception;
	void update(ShoppingCartReq req) throws Exception;
	void delete(Integer Id) throws Exception;
	
	// Get all cart items for a specific order
	List<ShoppingCartDTO> getAllByUserOrder(Integer userOrderId) throws Exception;
	
	// Get user's active cart (pending order items)
	List<ShoppingCartDTO> getActiveCartByUser(String userName) throws Exception;

}
