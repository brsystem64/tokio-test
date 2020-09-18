package com.example.api.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.dto.CustomerDto;

@Component
public class CustomerMapper implements IMapper<Customer, CustomerDto> {


	@Override
	public Customer dtoToEntity(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setId(dto.getId());
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());
		customer.setAddress(new ArrayList<Address>());
		
		return customer;
	}

}
