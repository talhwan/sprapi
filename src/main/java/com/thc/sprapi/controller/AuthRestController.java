package com.thc.sprapi.controller;

import com.thc.sprapi.security.ExternalProperties;
import com.thc.sprapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.auth0.jwt.exceptions.JWTVerificationException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import com.thc.sprapi.security.JwtTokenDto;

@Tag(name = "0-2. 시큐리티 권한 API 안내",
		description = "시큐리티 권한 기능 정의한 RestController.")
@RequestMapping("/api/auth")
@RestController
public class AuthRestController {
	
	private final AuthService authService;
	private final ExternalProperties externalProperties;
	
	public AuthRestController(AuthService authService, ExternalProperties externalProperties) {
		this.authService = authService;
		this.externalProperties = externalProperties;
	}
	
	@Operation(summary = "Access Token 발급을 위한 컨트롤러", 
			description = "Refresh Token이 헤더에 있고 해당 토큰이 유효하다면 Access Token 발급. <br />"
					+ "@param RefreshToken(String) =\\> 헤더에 담을것! <br />"
					+ "@return String Authorization(header), ResponseEntity\\<String\\> <br />"
					+ "@exception JWTVerificationException(Invalid Refresh Token) <br />"
			)
	@PostMapping("/access-token")
	public ResponseEntity<String> issueAccessToken(HttpServletRequest request, HttpServletResponse response) {
		ResponseEntity<String> responseEntity = null;
		String refreshToken = (String) request.getHeader(externalProperties.getRefreshKey());
		System.out.println("refreshToken: " + refreshToken);
		if(refreshToken == null || refreshToken.isEmpty() || !refreshToken.startsWith(externalProperties.getTokenPrefix())) {
			// 쿠키에 Refresh Token 없으면 return 406
			responseEntity = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		} else {
			try {
				refreshToken = refreshToken.substring(12, refreshToken.length());
				// 쿠키에 Refresh Token 있으면 검증 후 Access token 발급.
				JwtTokenDto jwtTokenDto = authService.issueAccessToken(refreshToken);
				// Access Token 발급 완료.
				// Access Token은 response header에 set.
				response.addHeader(externalProperties.getAccessKey(), externalProperties.getTokenPrefix() + jwtTokenDto.getAccessToken());
				
				// return 200(OK).
				responseEntity = ResponseEntity.status(HttpStatus.OK).build();
				//responseEntity = ResponseEntity.status(HttpStatus.OK).body(externalProperties.getTokenPrefix() + jwtTokenDto.getAccessToken());
			} catch (JWTVerificationException e) {
				e.printStackTrace();
				// Invalid Refresh Token
				responseEntity = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			}
		}
		return responseEntity;
	}
}