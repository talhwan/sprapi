package com.thc.sprapi.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thc.sprapi.security.JwtTokenDto;

public interface AuthService {
	
	Algorithm getTokenAlgorithm();
	String createAccessToken(String tbuserId);
	String verifyAccessToken(String accessToken) throws JWTVerificationException;
	String createRefreshToken(String tbuserId);
	String verifyRefreshToken(String refreshToken) throws JWTVerificationException;
	JwtTokenDto issueAccessToken(String refreshToken) throws JWTVerificationException;
	
}