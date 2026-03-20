package com.betacom.pr.models;

import com.betacom.pr.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
	
	@Getter
	@Setter
	@Entity
	@Table (name="sopping_cart")
	public class ShoppingCart {
		
		@Id
		private Integer idShoppingCart;
		
		@OneToOne
		@JoinColumn(name="id_User_Order")
		private UserOrder idUserOrder;
		
		@OneToMany
		@JoinColumn(name="id_Product")
		private Product idProduct;
		
		@Column (nullable = false)
		private Integer price;
		
		@Column (nullable = false)
		private Integer amount;

}
