package com.betacom.pr.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pr.dto.inputs.ShoppingCartReq;
import com.betacom.pr.dto.outputs.ShoppingCartDTO;
import com.betacom.pr.exceptions.WebServiceExceptions;
import com.betacom.pr.models.ShoppingCart;
import com.betacom.pr.repositories.IProductRepository;
import com.betacom.pr.repositories.IShoppingCartRepository;
import com.betacom.pr.repositories.IUserOrderRepository;
import com.betacom.pr.services.interfaces.IMessaggioServices;
import com.betacom.pr.services.interfaces.IShoppingCartServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShoppingCartImpl implements IShoppingCartServices {

    private final IShoppingCartRepository ssR;
    private final IProductRepository pR;
    private final IUserOrderRepository uoR;
    private final IMessaggioServices msgS;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ShoppingCartReq req) throws Exception {
        log.debug("create {}", req);

        ShoppingCart cart = new ShoppingCart();
        cart.setAmount(req.getAmount());
        cart.setPrice(req.getPrice());

        if (req.getIdProduct() != null) {
            cart.setProduct(pR.findById(req.getIdProduct())
                .orElseThrow(() -> new WebServiceExceptions(msgS.get("product_not_found"))));
        }

        if (req.getIdOrder() != null) {
            cart.setUserOrder(uoR.findById(req.getIdOrder())
                .orElseThrow(() -> new WebServiceExceptions(msgS.get("order_not_found"))));
        } else {
            cart.setUserOrder(null);
        }

        ssR.save(cart);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ShoppingCartReq req) throws Exception {
        log.debug("update {}", req);

        ShoppingCart us = ssR.findById(req.getId())
                .orElseThrow(() -> new WebServiceExceptions(msgS.get("cart_ntfnd"))); //user_ntfnd l'ho messo prima nella tabella messaggi_systema su DBeaver manualmente


        if (req.getAmount() != null)
            us.setAmount(req.getAmount());
        if (req.getPrice() != null)
            us.setPrice(req.getPrice());
        if (req.getIdOrder() != null)
            us.setUserOrder(uoR.findById(req.getIdOrder()).get());
        if (req.getIdProduct() != null)
            us.setProduct(pR.findById(req.getIdProduct()).get());

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
    public List<ShoppingCartDTO> getAllByUserOrder(Integer userOrderId) throws Exception {
        log.debug("get cart items for order: {}", userOrderId);
        
        List<ShoppingCart> cartItems = ssR.findAllByUserOrder_Id(userOrderId);
        
        return cartItems.stream()
            .map(cart -> ShoppingCartDTO.builder()
                    .id(cart.getId())
                    .idOrder(cart.getUserOrder().getId())
                    .idProduct(cart.getProduct().getId())
                    .price(cart.getPrice())
                    .amount(cart.getAmount())
                    .build()
            ).toList();
    }

    @Override
    public List<ShoppingCartDTO> getActiveCartByUser(String userName) throws Exception {
        log.debug("get active cart for user: {}", userName);
        
        List<ShoppingCart> cartItems = ssR.findActiveCartByUser(userName);
        
        return cartItems.stream()
            .map(cart -> ShoppingCartDTO.builder()
                    .id(cart.getId())
                    .idOrder(cart.getUserOrder().getId())
                    .idProduct(cart.getProduct().getId())
                    .price(cart.getPrice())
                    .amount(cart.getAmount())
                    .build()
            ).toList();
    }
    
	@Override
	public List<ShoppingCartDTO> getAll() {
	    List<ShoppingCart> entities = ssR.findAll();
	
	    return entities.stream().map(cart -> {
	        ShoppingCartDTO dto = ShoppingCartDTO.builder()
	                .id(cart.getId())
	                .amount(cart.getAmount())
	                .price(cart.getPrice())
	                .idProduct(cart.getProduct().getId())
	                .nome(cart.getProduct().getName())
	                .productStock(cart.getProduct().getStock()) 
	                .build();
	
	        if (cart.getProduct().getImages() != null && !cart.getProduct().getImages().isEmpty()) {
	            dto.setImmagine(cart.getProduct().getImages().get(0).getLink());
	        } else {      
	            dto.setImmagine("assets/placeholder.png");
	        }
	
	        return dto;
	    }).toList();
	}
}
