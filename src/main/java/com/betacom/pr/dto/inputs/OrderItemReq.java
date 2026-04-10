package com.betacom.pr.dto.inputs;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
public class OrderItemReq {
    private String productName;
    private Integer quantity;
    private Double price;
    private String imageUrl;
}
