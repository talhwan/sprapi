package com.thc.sprapi.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  유저 등급에 따른 예외처리
 */
@ResponseStatus(value=HttpStatus.LOCKED)
@SuppressWarnings("serial")
@NoArgsConstructor
public class TbuserProcessException extends RuntimeException {
	public TbuserProcessException(String message) {
		super(message);
	}
}