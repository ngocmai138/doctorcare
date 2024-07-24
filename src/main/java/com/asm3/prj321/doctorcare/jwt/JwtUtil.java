package com.asm3.prj321.doctorcare.jwt;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
//	private final SecretKey secret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	private SecretKey secretKey;
	
	@Value("${jwt.secret}")
	public void setSecretKey(String secret) {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
					.setClaims(claims)
					.setSubject(subject)
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
					.signWith(secretKey)
					.compact();
	}
	
	public Boolean validateToken(String token, UserDetails user) {
		final String userEmail = extractUserEmail(token);
		return (userEmail.equals(user.getUsername()) && !isTokenExpired(token));
	}
	
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	
	public String extractUserEmail(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

}
