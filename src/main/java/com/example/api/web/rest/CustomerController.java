package com.example.api.web.rest;

import java.util.List;
import java.util.Optional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.dto.CustomerDto;
import com.example.api.exception.ResourceNotFoundException;
import com.example.api.mapper.CustomerMapper;
import com.example.api.service.CustomerService;
import com.example.api.service.ViaCepService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;
	private ViaCepService viaCepService;

	@Autowired
	public CustomerController(CustomerService service, ViaCepService viaCepService) {
		this.service = service;
		this.viaCepService = viaCepService;
	}

	@GetMapping
	public Page<Customer> findAll(
			@RequestParam(value = "page", 
						  required = false,
						 defaultValue = "0") int page) {
		return service.findAll(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Customer> costumer = service.findById(id);
		if(costumer.isEmpty()) {
			throw new ResourceNotFoundException("Cannot find Costumer with id " + id);
		}
		
		return new ResponseEntity<Customer>(costumer.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CustomerDto customerdto){
		CustomerMapper mapper = new CustomerMapper();
		Customer customer = mapper.dtoToEntity(customerdto);
		
		customerdto.getAddress().forEach( addressFind -> {
			Address address = viaCepService.findByCep(addressFind.getCep());
			customer.getAddress().add(address);
		});

				
		try {
			return new ResponseEntity<Customer>(service.create(customer), HttpStatus.CREATED);
		}catch(Exception e) {
			throw new ValidationException(e);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id ,@RequestBody CustomerDto customerdto){
		CustomerMapper mapper = new CustomerMapper();
		
		service.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Cannot find Costumer with id " + id));		

		Customer customer = mapper.dtoToEntity(customerdto);
		customer.setId(id);
		
		customerdto.getAddress().forEach( addressFind -> {
			Address address = viaCepService.findByCep(addressFind.getCep());
			customer.getAddress().add(address);
		});
		
		return new ResponseEntity<Customer>(service.update(customer), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Cannot find Costumer with id " + id));				
		service.delete(id);
		return new ResponseEntity<String>("Customer deleted!", HttpStatus.ACCEPTED);

	}	
}
