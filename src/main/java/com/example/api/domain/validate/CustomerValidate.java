package com.example.api.domain.validate;

import org.springframework.stereotype.Component;

import com.example.api.domain.Customer;
import com.example.api.exception.ValidateException;

@Component
public class CustomerValidate implements IValidate<Customer> {

	private Customer customer;
	public void execute(Customer obj) throws ValidateException {
		customer = obj;
		
		isObjectNull();
		isNomeNull();
		isEmailNull();
	}
	
	private void isObjectNull(){
		if(customer == null) {
			throw new ValidateException("Body cannot be Null!");
		}
	}
	
	private void isNomeNull(){
		if(customer.getName().isBlank() || customer.getName().isEmpty()) {
			throw new ValidateException("Name cannot be Null!");			
		}
	}

	private void isEmailNull(){
		if(customer.getEmail().isBlank() || customer.getEmail().isEmpty()) {
			throw new ValidateException("Email cannot be Null!");			
		}
	}
}
