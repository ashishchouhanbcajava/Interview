package com.Learning.Learning.Configurations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import com.Learning.Learning.beans.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtConfig {

	private static final String secret = "yutryuewgrkwefbdhfhsdfghsgdfjsghd";
//	private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour
	private static final long EXPIRATION = 1000 * 60 * 2; // 2 minuts

	public static String generateToken(Users users) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("role", users.getRole());
		return Jwts.builder().subject(users.getUsername()).claims(claims).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
	}

	public static Claims extractclaims(String token) {
		return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(token)
				.getPayload();
	}

	public static boolean isExpired(String token) {
		Claims claims = extractclaims(token);
		Date expiration = claims.getExpiration();
		return expiration.before(new Date());
	}
}
