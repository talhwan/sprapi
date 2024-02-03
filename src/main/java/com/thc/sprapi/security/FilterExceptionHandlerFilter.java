package com.thc.sprapi.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.TokenExpiredException;

public class FilterExceptionHandlerFilter extends OncePerRequestFilter {

	/**
     *  TokenExpiredException 핸들링을 위한 필터
	 *  상태코드 UNAUTHORIZED(401)을 response에 담아 리턴한다
	 */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
        	System.out.println("filter start!!!");
        	System.out.println("filter request!!!" + request.getAttribute("credential"));

            filterChain.doFilter(request, response);
        }catch (TokenExpiredException e){
            System.out.println("filter UNAUTHORIZED!!!");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            try{
                response.getWriter().write("Invalid Access Token");
            }catch (IOException i){
                i.printStackTrace();
            }
        }
    }
	
}