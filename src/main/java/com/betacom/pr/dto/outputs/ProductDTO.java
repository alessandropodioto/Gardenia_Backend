package com.betacom.pr.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class ProductDTO {
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private Integer stock;
	private Boolean is_deleted;
	
    private Integer subcategoryId;
    private String subcategoryName;
    
}
