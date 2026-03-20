package com.betacom.pr.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class UserOrderReq {
	
	private Integer id;
    private String wharehouse;
    private Boolean isPaid;   
    private String userId;
    private Integer addressId;
    private Integer statusId;

    private List<ShoppingCartReq> products; 
}