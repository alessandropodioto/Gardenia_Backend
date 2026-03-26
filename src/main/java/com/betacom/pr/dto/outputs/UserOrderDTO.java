package com.betacom.pr.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserOrderDTO {

    private Integer id;
    private String wharehouse;
    private Boolean isPaid;    
    private String userName;  
    private Integer addressId; 
    private String statusDescription;
    private String date;
}