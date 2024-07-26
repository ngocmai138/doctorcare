package com.asm3.prj321.doctorcare.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.asm3.prj321.doctorcare.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	@Lazy
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		List<String> urls =  Arrays.asList("/oauth2/token",
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
											"/swagger-ui/index.html");
		String url = request.getRequestURI();
		if (urls.contains(url)) {
			filterChain.doFilter(request, response);
			return;
		}
		final String authorizationHeader = request.getHeader("Authorization");
		String userEmail = null;
		String jwt = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jwt = authorizationHeader.substring(7);
			userEmail = jwtUtil.extractUserEmail(jwt);
		} else {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Missing or invalid Authorization header");
			return;
		}
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = this.userService.loadUserByUsername(userEmail);
			if (jwtUtil.validateToken(jwt, user)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, " Invalid JWT Token");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

}
