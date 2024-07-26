package com.asm3.prj321.doctorcare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.asm3.prj321.doctorcare.jwt.JwtRequestFilter;
import com.asm3.prj321.doctorcare.service.UserService;

@Configuration 
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	@Lazy
	private UserService userService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
			http
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/oauth2/token",
											"/oauth2/register",
											"/oauth2/verifyEmail",
											"/oauth2/resetPassword",
											"/v3/api-docs/**",
											"/swagger-ui/**",
											"/swagger-ui.html",
											"/swagger-resources/**",
											"/swagger-resources/configuration/ui",
											"/swagger-resources/configuration/security",
											"/webjars/**",
											"/swagger-ui/index.html")
						.permitAll()
						.requestMatchers("/doctor/**")
						.hasRole("Doctor")
						.anyRequest()
						.authenticated()
						)
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
				
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
