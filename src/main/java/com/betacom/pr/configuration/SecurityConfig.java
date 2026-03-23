package com.betacom.pr.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    /*@Bean
	    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
			http.authorizeHttpRequests((requests) -> requests
					.requestMatchers("/admin", "/admin/**", "/product").hasRole("ADMIN")
					.requestMatchers("/", "/address", "/category", "/image", "/shoppingCart", "/status", "/subcategory", "/user", "/order").permitAll()
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
	}*/
}
