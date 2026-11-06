package com.Learning.Learning.Configurations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Learning.Learning.Service.UsersService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private UsersService usersService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String tokenHeader = request.getHeader("Authorization");
		String token = tokenHeader.substring(7);

		try {

			if (!JwtConfig.isExpired(token)) {

				Claims claims = JwtConfig.extractclaims(token);
				String username = claims.getSubject();
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails user = usersService.loadUserByUsername(username);
					if (user != null) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								user.getUsername(), user.getPassword(), user.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}

				}

			}
		} catch (ExpiredJwtException ex) {
			response.setStatus(401);
			response.getWriter().write("Token is expired. Please login again.");
			return;
		} catch (JwtException ex) {
			response.setStatus(401);
			response.getWriter().write("Invalid JWT token");
			return;
		}

		filterChain.doFilter(request, response);
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		// TODO Auto-generated method stub

		System.out.println("pathhh : " + request.getServletPath());
		return request.getServletPath().startsWith("/api/users/");
	}

}
