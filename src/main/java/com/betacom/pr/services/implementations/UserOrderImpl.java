package com.betacom.pr.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pr.dto.inputs.UserOrderReq;
import com.betacom.pr.dto.outputs.UserOrderDTO;
import com.betacom.pr.enums.Status;
import com.betacom.pr.exceptions.WebServiceExceptions;
import com.betacom.pr.models.Address;
import com.betacom.pr.models.User;
import com.betacom.pr.models.UserOrder;
import com.betacom.pr.repositories.IAddressRepository;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(UserOrderReq req) throws Exception {
        log.debug("create order: {}", req);

        User user = userR.findById(req.getUserId())
                .orElseThrow(() -> new Exception("User not found: " + req.getUserId()));
        
        Address address = addR.findById(req.getAddressId())
                .orElseThrow(() -> new Exception("Address not found: " + req.getAddressId()));

        UserOrder order = new UserOrder();
        order.setWharehouse(req.getWharehouse());
        order.setIsPaid(false);
        order.setUser(user);
        order.setAddress(address);
        order.setStatus(Status.PENDING);

        orderR.save(order);
    }

    @Override
    public UserOrderDTO getById(Integer id) throws Exception {
        log.debug("get order by id: {}", id);

        UserOrder order = orderR.findById(id)
            .orElseThrow(() -> new Exception("Ordine non trovato"));

        return UserOrderDTO.builder()
                        .id(order.getId())
                        .wharehouse(order.getWharehouse())
                        .isPaid(order.getIsPaid())
                        .userName(order.getUser().getUserName())
                        .addressId(order.getAddress().getId())
                        .statusDescription(order.getStatus().toString())
                        .build();
    }

    @Override
    public List<UserOrderDTO> getByUserId(User userId) {
        log.debug("list orders for user: {}", userId);
        return orderR.findAllByUser_UserName(userId.getUserName()).stream()
                .map(order -> UserOrderDTO.builder()
                        .id(order.getId())
                        .wharehouse(order.getWharehouse())
                        .isPaid(order.getIsPaid())
                        .userName(order.getUser().getUserName())
                        .addressId(order.getAddress().getId())
                        .statusDescription(order.getStatus().toString())
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
                        .addressId(order.getAddress().getId())
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
		if(req.getUserId() !=null)
			us.setUser(userR.findById(req.getUserId()).get());
		if(req.getAddressId() != null)
			us.setAddress(addR.findById(req.getAddressId()).get());
		if(req.getStatus() != null)
			us.setStatus(Status.valueOf(req.getStatus()));
		

		orderR.save(us);
		
	}
    
}
