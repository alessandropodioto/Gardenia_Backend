package com.betacom.pr.services.implementations;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.pr.dto.inputs.UserOrderReq;
import com.betacom.pr.dto.outputs.UserOrderDTO;
import com.betacom.pr.models.Address;
import com.betacom.pr.models.Status;
import com.betacom.pr.models.User;
import com.betacom.pr.models.UserOrder;
import com.betacom.pr.repositories.IAddressRepository;
import com.betacom.pr.repositories.IStatusRepository;
import com.betacom.pr.repositories.IUserOrderRepository;
import com.betacom.pr.repositories.IUserRepository;
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
    private final IStatusRepository stR;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(UserOrderReq req) throws Exception {
        log.debug("create order: {}", req);

        User user = userR.findById(req.getUserId())
                .orElseThrow(() -> new Exception("User not found: " + req.getUserId()));
        
        Address address = addR.findById(req.getAddressId())
                .orElseThrow(() -> new Exception("Address not found: " + req.getAddressId()));
        
        Status status = stR.findById(req.getStatusId())
                .orElseThrow(() -> new Exception("Status not found: " + req.getStatusId()));

        UserOrder order = new UserOrder();
        order.setWharehouse(req.getWharehouse());
        order.setIsPaid(req.getIsPaid());
        order.setUser(user);
        order.setAddress(address);
        order.setStatus(status);

        orderR.save(order);
    }

    @Override
    @Transactional
    public void updateStatus(Integer orderId, Integer statusId) {
        log.debug("update status of order {} to {}", orderId, statusId);
        
        orderR.findById(orderId).ifPresent(order -> 
            stR.findById(statusId).ifPresent(newStatus -> {
                order.setStatus(newStatus);
                orderR.save(order);
            })
        );
    }

    @Override
    public UserOrderDTO getById(Integer id) {
        log.debug("get order by id: {}", id);
        return orderR.findById(id)
                .map(order -> this.convertToDTO(order))
                .orElse(null);
    }

    @Override
    public List<UserOrderDTO> getByUserId(String userId) {
        log.debug("list orders for user: {}", userId);
        return orderR.findByUserUserName(userId).stream()
                .map(order -> this.convertToDTO(order))
                .toList();
    }

    @Override
    public List<UserOrderDTO> getAll() {
        log.debug("list all orders");
        return orderR.findAll().stream()
                .map(order -> this.convertToDTO(order))
                .toList();
    }

    private UserOrderDTO convertToDTO(UserOrder order) {
        UserOrderDTO dto = new UserOrderDTO();
        dto.setId(order.getId());
        dto.setWharehouse(order.getWharehouse());
        dto.setIsPaid(order.getIsPaid());
        dto.setUserName(order.getUser().getUserName());
        dto.setAddressId(order.getAddress().getId());
        dto.setStatusDescription(order.getStatus().getDescription());
        return dto;
    }
    
}
