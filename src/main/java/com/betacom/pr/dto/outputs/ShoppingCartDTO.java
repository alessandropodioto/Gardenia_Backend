package com.betacom.pr.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ShoppingCartDTO {

	private Integer id;
	private Integer idOrder;
	private Integer idProduct;
	private Double price;
	private Integer amount;
	
	private String nome;
    private String immagine;
	
}
