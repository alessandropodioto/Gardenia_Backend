package com.betacom.pr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
			http.authorizeHttpRequests((requests) -> requests
					.requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
					.requestMatchers("/", "/address", "/category", "/image", "/product", "/shoppingCart", "/status", "/subcategory", "/user", "/order").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin((form) -> form
					.loginPage("/login")
					.permitAll()
					)
			.logout((logout) -> logout.permitAll());
			
			return http.build();
		}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
