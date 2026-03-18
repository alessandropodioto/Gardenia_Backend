package com.betacom.pr.models;

import com.betacom.pr.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name="user")
public class User {
	
	@Id
	private String userName;
	
	@Column (length = 100, nullable = false)
	private String firstName;
	
	@Column (length = 100, nullable = false)
	private String lastName;
	
	@Column (length = 100, nullable = false, unique = true)
	private String email;
	
	@Column (length = 100, nullable = false)
	private String phone;
	
	@Column (length = 20, nullable = false)
	private String password;
	
	@Column (length = 100, nullable = false, unique = true)
	private Roles role;
	
	@OneToMany
	@JoinColumn(name="id_address")
	private Address idAddress;
	
}
