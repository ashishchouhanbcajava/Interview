package com.Learning.Learning.Service;

import org.springframework.stereotype.Service;

import com.Learning.Learning.Repository.RefreshTokenDetailsRepository;
import com.Learning.Learning.beans.RefreshTokenDetails;

@Service
public class RefreshTokenDetailsService {

	private final RefreshTokenDetailsRepository tokenDetailsRepository;

	public RefreshTokenDetailsService(RefreshTokenDetailsRepository tokenDetailsRepository) {
		// TODO Auto-generated constructor stub
		this.tokenDetailsRepository = tokenDetailsRepository;
	}

	public RefreshTokenDetails saveTokenDetails(RefreshTokenDetails refreshTokenDetails) {
		return tokenDetailsRepository.save(refreshTokenDetails);
	}
	
	
}
