package com.Learning.Learning.beans;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RefreshTokenDetails {

	@Id
	private String id;
	private String userId;
	private Boolean Isrevoked;
	private String tokenHash;
	private LocalDateTime expiration;
	private String sessionFamily;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsrevoked() {
		return Isrevoked;
	}

	public void setIsrevoked(Boolean isrevoked) {
		Isrevoked = isrevoked;
	}

	public String getTokenHash() {
		return tokenHash;
	}

	public void setTokenHash(String tokenHash) {
		this.tokenHash = tokenHash;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}

	public String getSessionFamily() {
		return sessionFamily;
	}

	public void setSessionFamily(String sessionFamily) {
		this.sessionFamily = sessionFamily;
	}

}
