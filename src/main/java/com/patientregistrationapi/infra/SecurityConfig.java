package com.patientregistrationapi.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		 http
         .csrf(csrf -> csrf.disable()) // Desativa CSRF usando lambda DSL
         .sessionManagement(session -> 
             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura política de sessão
         );
     
     return http.build();
	}
}
