package com.betacom.pr.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder

public class ShoppingCartDTO {

	private Integer id;
	private Integer idOrder;
	private Integer idProduct;
	private Double price;
	private Integer amount;
	
}
