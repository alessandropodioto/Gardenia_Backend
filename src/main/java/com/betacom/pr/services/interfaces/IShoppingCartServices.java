package com.betacom.pr.services.interfaces;

import com.betacom.pr.dto.inputs.ShoppingCartReq;
import com.betacom.pr.dto.outputs.ShoppingCartDTO;
import com.betacom.pr.dto.outputs.UserDTO;
import com.betacom.pr.dto.outputs.UserOrderDTO;
import java.util.List;


public interface IShoppingCartServices {
	
	void create(ShoppingCartReq req) throws Exception;
	void update(ShoppingCartReq req) throws Exception;
	void delete(Integer Id) throws Exception;
	
	List<ShoppingCartDTO> getAllByUserOrder(UserOrderDTO userOrder) throws Exception;

}
