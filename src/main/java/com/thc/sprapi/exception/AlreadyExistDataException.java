package com.thc.sprapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

/**
 *  등록할 데이터 uq가 이미 있을 경우 사용되는 오류
 *  HttpStatus 409
 */
@ResponseStatus(value=HttpStatus.CONFLICT)
@SuppressWarnings("serial")
@NoArgsConstructor
public class AlreadyExistDataException extends RuntimeException {
	public AlreadyExistDataException(String message) {
		super(message);
	}
}