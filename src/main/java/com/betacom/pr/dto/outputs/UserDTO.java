package com.betacom.pr.dto.outputs;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDTO {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String password;
	private String role;
	private List<Integer> idAddresses;

}
