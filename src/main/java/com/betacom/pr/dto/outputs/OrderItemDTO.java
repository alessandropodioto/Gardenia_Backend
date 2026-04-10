package com.betacom.pr.dto.outputs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private String productName;
    private Integer quantity;
    private Double price;
    private String imageUrl;
}
