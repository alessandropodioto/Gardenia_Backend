package com.betacom.pr.dto.inputs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserOrderReq {
	
	private Integer id;
    private String wharehouse;
    private Boolean isPaid;   
    private String userId;
    private Integer addressId;
    private String status;

    private List<ShoppingCartReq> products; 
}