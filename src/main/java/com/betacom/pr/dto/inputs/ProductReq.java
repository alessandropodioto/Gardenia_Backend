package com.betacom.pr.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductReq {
    private Integer id; 
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    
    private Integer categoryId;
    private Integer subcategoryId;
    private Integer imageId;
}