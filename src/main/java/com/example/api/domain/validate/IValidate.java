package com.example.api.domain.validate;

import org.springframework.stereotype.Component;

import com.example.api.exception.ValidateException;

@Component
public interface IValidate<T> {
	
	public void execute(T obj) throws ValidateException;

}
