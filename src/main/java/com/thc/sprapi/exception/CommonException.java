package com.thc.sprapi.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter @Setter
@NoArgsConstructor
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
public class CommonException extends RuntimeException{

    public CommonException(String msg){
        super(msg);
    }
    public CommonException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public CommonException(Throwable cause) {
        super(cause);
    }
}
