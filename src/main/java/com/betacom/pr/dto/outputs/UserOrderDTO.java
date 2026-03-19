package com.betacom.pr.dto.outputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
public class UserOrderDTO {

    private Integer id;
    private String wharehouse;
    private Boolean isPaid;    
    private String userName;  
    private Integer addressId; 
    private String statusDescription;
    private List<ShoppingCartDTO> products;
}