package com.betacom.pr.services.interfaces;

import java.util.List;

import com.betacom.pr.dto.inputs.ShoppingCartReq;
import com.betacom.pr.dto.outputs.ShoppingCartDTO;
import com.betacom.pr.dto.outputs.UserOrderDTO;


public interface IShoppingCartServices {
	
	void create(ShoppingCartReq req) throws Exception;
	void update(ShoppingCartReq req) throws Exception;
	void delete(Integer Id) throws Exception;
	
	List<ShoppingCartDTO> getAllByUserOrder(UserOrderDTO userOrder) throws Exception;

}
