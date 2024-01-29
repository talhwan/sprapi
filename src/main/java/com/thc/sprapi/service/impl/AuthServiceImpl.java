package com.thc.sprapi.service.impl;

import java.util.Date;

import com.thc.sprapi.exception.InvalidTokenException;
import com.thc.sprapi.security.ExternalProperties;
import com.thc.sprapi.security.JwtTokenDto;
import com.thc.sprapi.service.AuthService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class AuthServiceImpl implements AuthService {

	private final ExternalProperties externalProperties;
	public AuthServiceImpl(
			ExternalProperties externalProperties
	) {
		this.externalProperties = externalProperties;
	}
	/*
	private final RefreshTokenRepository refreshTokenRepository;
	public AuthServiceImpl(
			RefreshTokenRepository refreshTokenRepository
		) {
		this.refreshTokenRepository = refreshTokenRepository;
	}
	 */
	
	@Override
	public Algorithm getTokenAlgorithm() {
		return Algorithm.HMAC512(externalProperties.getTokenSecretKey());
	}
	
	/**
	 * 	Access Token 생성을 위한 함수.
	 *  Payload에 tbuser Id를 담는다. 
	 *  
	 */
	@Override
	public String createAccessToken(String tbuserId) {
    	return JWT.create()
 			 	  .withSubject("accessToken")
 			 	  .withClaim("id", tbuserId)
 			 	  .withExpiresAt(new Date(System.currentTimeMillis()+externalProperties.getAccessTokenExpirationTime()))
 			 	  .sign(getTokenAlgorithm());
	}

    /**
	 * 	Access Token 검증을 위한 함수
	 *   
	 */
	@Override
	public String verifyAccessToken(String accessToken) throws JWTVerificationException {
		return JWT.require(getTokenAlgorithm())
				.build()
				.verify(accessToken)
				.getClaim("id").asString();
	}

    /**
	 * 	Refresh Token 생성을 위한 함수.
	 *  Payload에 tbuser Id를 담는다.
	 *  DB에 저장할수도 있음.
	 *  redis에 tbuserId를 key로, 발급한 Refresh Token을 value로 저장할 수도있음.
	 *  
	 */
	@Override
	public String createRefreshToken(String tbuserId) {
    	String refreshToken = JWT.create()
			     				 .withSubject("refreshToken")
			     				 .withClaim("id", tbuserId)
			     				 .withExpiresAt(new Date(System.currentTimeMillis()+externalProperties.getRefreshTokenExpirationTime()))
			     				 .sign(getTokenAlgorithm());
		//디비나 redis에 저장하는 과정 추가 가능!!
		return refreshToken;
	}

    /**
	 * 	Refresh Token 검증을 위한 함수
	 *   
	 */
	@Override
	public String verifyRefreshToken(String refreshToken) throws JWTVerificationException {
		refreshToken = refreshToken.substring(12, refreshToken.length());
		return JWT.require(getTokenAlgorithm())
				.build()
				.verify(refreshToken)
				.getClaim("id").asString();
	}
	
	/**
	 * 	Access Token 발급을 위한 함수.
	 *  Refresh Token이 유효하다면 Access Token 발급.
	 *  
	 */
	@Override
	public JwtTokenDto issueAccessToken(String refreshToken) throws JWTVerificationException {
		// Refresh Token 검증(실패시 throws JWTVerificationException)
		//System.out.println("refresh : " +refreshToken);
		String tbuserId = verifyRefreshToken(refreshToken);
		// Access Token 생성
		String accessToken = createAccessToken(tbuserId);
		
		return JwtTokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}

}