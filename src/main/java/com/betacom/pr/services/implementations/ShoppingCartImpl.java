package com.betacom.pr.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pr.dto.inputs.ShoppingCartReq;
import com.betacom.pr.dto.inputs.UserOrderReq;
import com.betacom.pr.dto.inputs.UserReq;
import com.betacom.pr.dto.outputs.ShoppingCartDTO;
import com.betacom.pr.dto.outputs.UserOrderDTO;
import com.betacom.pr.enums.Roles;
import com.betacom.pr.exceptions.WebServiceExceptions;
import com.betacom.pr.models.Address;
import com.betacom.pr.models.Product;
import com.betacom.pr.models.ShoppingCart;
import com.betacom.pr.models.Status;
import com.betacom.pr.models.User;
import com.betacom.pr.models.UserOrder;
import com.betacom.pr.repositories.IAddressRepository;
import com.betacom.pr.repositories.IProductRepository;
import com.betacom.pr.repositories.IShoppingCartRepository;
import com.betacom.pr.repositories.IUserOrderRepository;
import com.betacom.pr.repositories.IUserRepository;
import com.betacom.pr.services.interfaces.IMessaggioServices;
import com.betacom.pr.services.interfaces.IShoppingCartServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShoppingCartImpl implements IShoppingCartServices{
	
	private final IShoppingCartRepository ssR;
	private final IProductRepository pR;
	private final IUserOrderRepository uoR;
	private final IMessaggioServices msgS;
	
		@Override
	    @Transactional(rollbackFor = Exception.class)
	    public void create(ShoppingCartReq req) throws Exception {
		 
		 log.debug("create {}", req);
			if(ssR.existsById(req.getId()))
				throw new WebServiceExceptions(msgS.get("user_exist")); 
	       
	        ShoppingCart cart = new ShoppingCart();
	        cart.setAmount(req.getAmount());
	        cart.setPrice(req.getPrice());
	        cart.setIdProduct(pR.findById(req.getIdProduct()).get());
	        cart.setIdUserOrder(uoR.findById(req.getIdOrder()).get());
	        
	        ssR.save(cart);
	    }
	    
	    @Override
	    @Transactional(rollbackFor = Exception.class)
		public void update(ShoppingCartReq req) throws Exception {
			log.debug("update {}", req);

			ShoppingCart us = ssR.findById(req.getId()) 
					.orElseThrow(() -> new WebServiceExceptions(msgS.get("cart_ntfnd"))); //user_ntfnd l'ho messo prima nella tabella messaggi_systema su DBeaver manualmente


			if(req.getAmount() != null)
				us.setAmount(req.getAmount());
			if(req.getPrice() != null)
				us.setPrice(req.getPrice());
			if(req.getIdOrder() !=null)
				us.setIdUserOrder(uoR.findById(req.getIdOrder()).get());
			if(req.getIdProduct() != null)
				us.setIdProduct(pR.findById(req.getIdProduct()).get());

			ssR.save(us);

		}
	    
	    @Override
	    @Transactional(rollbackFor = Exception.class)
		public void delete(Integer Id) throws Exception {
			log.debug("delete {}", Id);
			ShoppingCart us = ssR.findById(Id)
					.orElseThrow(() -> new WebServiceExceptions(msgS.get("cart_ntfnd"))); 

			ssR.delete(us);

		}

		@Override
		public List<ShoppingCartDTO> getAllByUserOrder(UserOrderDTO userOrder) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
	    

}
