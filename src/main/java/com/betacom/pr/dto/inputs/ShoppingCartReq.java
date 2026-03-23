package com.betacom.pr.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
	
public class ShoppingCartReq {
	
	private Integer id;
	private Integer idOrder;
	private Integer idProduct;
	private Integer amount;
	private Integer price;
	
}
