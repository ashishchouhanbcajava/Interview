package com.Learning.Learning.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter authFilter;

	@Bean
	SecurityFilterChain firstFilter(HttpSecurity http) {
		return http.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(
						(request) -> request.requestMatchers("/api/users/**").permitAll().anyRequest().authenticated())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

}
