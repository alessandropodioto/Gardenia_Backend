package com.betacom.pr.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pr.dto.inputs.OrderItemReq;
import com.betacom.pr.dto.inputs.UserOrderReq;
import com.betacom.pr.dto.outputs.OrderItemDTO;
import com.betacom.pr.dto.outputs.UserOrderDTO;
import com.betacom.pr.enums.Status;
import com.betacom.pr.exceptions.WebServiceExceptions;
import com.betacom.pr.models.OrderItem;
import com.betacom.pr.models.User;
import com.betacom.pr.models.UserOrder;
import com.betacom.pr.repositories.IAddressRepository;
import com.betacom.pr.repositories.IOrderItemRepository;
import com.betacom.pr.repositories.IUserOrderRepository;
import com.betacom.pr.repositories.IUserRepository;
import com.betacom.pr.services.interfaces.IMessaggioServices;
import com.betacom.pr.services.interfaces.IUserOrderServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserOrderImpl implements IUserOrderServices {
	
    private final IUserOrderRepository orderR;
    private final IUserRepository userR;
    private final IAddressRepository addR;
    private final IMessaggioServices msgS;
    private final IOrderItemRepository orderItemR;

    @Override
    @Transactional
    public void create(UserOrderReq req) throws Exception {

        User user = userR.findById(req.getUserId())
                .orElseThrow(() -> new Exception("Utente non trovato: " + req.getUserId()));

        UserOrder order = new UserOrder();
        order.setUser(user);
        order.setDate(req.getDate());
        order.setTotalPrice(req.getTotalPrice());
        order.setWharehouse(req.getWharehouse());
        
        if (req.getStatus() != null) {
            order.setStatus(Status.valueOf(req.getStatus().toUpperCase()));
        } else {
            order.setStatus(Status.PENDING);
        }
        
        order.setIsPaid(req.getIsPaid() != null ? req.getIsPaid() : false);

        UserOrder savedOrder = orderR.save(order);

        if (req.getItems() != null && !req.getItems().isEmpty()) {
            for (OrderItemReq itemReq : req.getItems()) {
                OrderItem item = new OrderItem();
                item.setOrder(savedOrder);
                item.setProductName(itemReq.getProductName());
                item.setQuantity(itemReq.getQuantity());
                item.setPrice(itemReq.getPrice());
                item.setImageUrl(itemReq.getImageUrl());

                orderItemR.save(item);
            }
        }
    }

    @Override
    public UserOrderDTO getById(Integer id) throws Exception {
        log.debug("get order by id: {}", id);

        UserOrder order = orderR.findById(id)
            .orElseThrow(() -> new Exception("Ordine non trovato"));
        List<OrderItemDTO> itemsList = null;
        if (order.getArticoli() != null) {
            itemsList = order.getArticoli().stream().map(art -> 
                OrderItemDTO.builder()
                    .productName(art.getProductName())
                    .quantity(art.getQuantity())
                    .price(art.getPrice())
                    .imageUrl(art.getImageUrl())
                    .build()
            ).toList();
        }

        // 2. Aggiungiamo .items(itemsList) al builder
        return UserOrderDTO.builder()
                        .id(order.getId())
                        .wharehouse(order.getWharehouse())
                        .isPaid(order.getIsPaid())
                        .userName(order.getUser().getUserName())
                        .addressId(order.getAddress() != null ? order.getAddress().getId() : null)
                        .date(order.getDate())
                        .totalPrice(order.getTotalPrice()) // Assicurati che ci sia anche il totale!
                        .statusDescription(order.getStatus().toString())
                        .items(itemsList) // <--- Fondamentale: senza questo Angular vede vuoto
                        .build();
    }

    @Override
    public List<UserOrderDTO> getByUserId(String userName) {
        log.debug("list orders for user: {}", userName);
        return orderR.findAllByUser_UserName(userName).stream()
                .map(order -> UserOrderDTO.builder()
                        .id(order.getId())
                        .wharehouse(order.getWharehouse())
                        .isPaid(order.getIsPaid())
                        .userName(order.getUser().getUserName())
                        .date(order.getDate())
                        .statusDescription(order.getStatus().toString())
                        .totalPrice(order.getTotalPrice()) 
                        .build()
                ).toList();
    }

    @Override
    public List<UserOrderDTO> getAll() {
        log.debug("list all orders");
        return orderR.findAll().stream()
                .map(order -> UserOrderDTO.builder()
                        .id(order.getId())
                        .wharehouse(order.getWharehouse())
                        .isPaid(order.getIsPaid())
                        .userName(order.getUser().getUserName())
                        .addressId(order.getAddress() != null ? order.getAddress().getId() : null)
                        .date(order.getDate())
                        .statusDescription(order.getStatus().toString())
                        .build()
                ).toList();
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(UserOrderReq req) throws Exception {
		log.debug("update {}", req);

		UserOrder us = orderR.findById(req.getId())
				.orElseThrow(() -> new WebServiceExceptions(msgS.get("order_ntfnd"))); //user_ntfnd l'ho messo prima nella tabella messaggi_systema su DBeaver manualmente


		if(req.getWharehouse() != null)
			us.setWharehouse(req.getWharehouse());
		if(req.getIsPaid() != null) {
            if (req.getIsPaid())
                us.setStatus(Status.PENDING);
			us.setIsPaid(req.getIsPaid());
        }
		if(req.getDate()!=null)
			us.setDate(req.getDate());
		if(req.getUserId() !=null)
			us.setUser(userR.findById(req.getUserId()).get());
		if(req.getAddressId() != null)
			us.setAddress(addR.findById(req.getAddressId()).get());
		if(req.getStatus() != null)
			us.setStatus(Status.valueOf(req.getStatus()));
		

		orderR.save(us);
		
	}

	@Override
	public void updateStatus(UserOrderReq req) throws Exception {
		log.debug("updateStatus {}", req);

		UserOrder us = orderR.findById(req.getId())
				.orElseThrow(() -> new WebServiceExceptions(msgS.get("order_ntfnd")));
		
            if (req.getIsPaid())
    			us.setStatus(Status.valueOf(req.getStatus()));
	}
}
