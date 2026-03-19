package com.betacom.pr.dto.outputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubcategoryDTO {
	 private Integer subcategoryId;
	 private String subcategoryName;
	 private Integer CategoryId;
}
