package com.Learning.Learning.Configurations;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Configuration
public class RefreshTokenConfig {

	private static final String SECRET = "HelloiamrefreshTokensecret";
	private static final long REFRESHTOKENEXPIRY = 1000 * 60 * 4; // 4 minuts

	public String generateRefreshToken(String username) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("tokenType", "refreshToken");

		return Jwts.builder().subject(username).claims(claims).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + REFRESHTOKENEXPIRY))
				.signWith(Keys.hmacShaKeyFor(SECRET.getBytes())).compact();
	}

}
