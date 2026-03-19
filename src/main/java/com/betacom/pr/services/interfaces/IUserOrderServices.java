package com.betacom.pr.services.interfaces;

import java.util.List;
import com.betacom.pr.dto.inputs.UserOrderReq;
import com.betacom.pr.dto.outputs.UserOrderDTO;

public interface IUserOrderServices {

    void create(UserOrderReq req) throws Exception;
    void updateStatus(Integer orderId, Integer statusId);
    UserOrderDTO getById(Integer id);
    List<UserOrderDTO> getByUserId(String userId);
    List<UserOrderDTO> getAll();
}