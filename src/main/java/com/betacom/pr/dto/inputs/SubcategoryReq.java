package com.betacom.pr.dto.inputs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SubcategoryReq {
	private Integer subcategoryId;
	 private String subcategoryName;
	 private Integer CategoryId;
}
