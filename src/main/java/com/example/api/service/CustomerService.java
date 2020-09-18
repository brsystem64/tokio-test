package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.domain.validate.CustomerValidate;
import com.example.api.domain.validate.IValidate;
import com.example.api.repository.CustomerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


@Service
public class CustomerService {

	private CustomerRepository repository;
	private CustomerValidate validate;

	@Autowired
	public CustomerService(CustomerRepository repository, CustomerValidate validate){
		this.repository = repository;
		this.validate = validate;
	}

	public Page<Customer> findAll(int page) {
		PageRequest pageRequest = PageRequest.of(page, 3);
        return repository.findAll(pageRequest);
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}
	
	public Customer update(Customer customer) {
		validate.execute(customer);
		return repository.save(customer);
	}
	
	public Customer create(Customer customer) {
		validate.execute(customer);
		return repository.save(customer);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
}
