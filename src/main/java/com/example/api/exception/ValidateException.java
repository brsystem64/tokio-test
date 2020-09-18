package com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ValidateException extends RuntimeException{
	
	public ValidateException(String msg) {
		super(msg);
	}

}
