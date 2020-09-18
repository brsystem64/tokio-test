package com.example.api.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.domain.Address;
import com.example.api.dto.CepDto;
import com.example.api.exception.ResourceNotFoundException;
import com.example.api.exception.ValidateException;
import com.example.api.mapper.AddressMapper;

@Service
public class ViaCepService {
	
	private final String URL_BASE = "https://viacep.com.br/ws/";
	private final String TYPE_REQUEST = "/json/";

	
	
	public Address findByCep(String cep) {
		if(cep.isBlank() || cep.isEmpty()) {
			throw new ValidateException("Cep cannot be null!");
		}
		
		cep = cep.replace("-", "");
		CepDto cepdto;
	     
		try {
			RestTemplate restTemplate = new RestTemplate();	      
		      cepdto = restTemplate.exchange(URL_BASE + cep + TYPE_REQUEST, HttpMethod.GET, null, new ParameterizedTypeReference<CepDto>() {
			}).getBody();
		     
		}catch(Exception e) {
	    	  throw new ResourceNotFoundException("Cep not  found!");
		}

	      AddressMapper mapper = new AddressMapper();
	      return mapper.dtoToEntity(cepdto);
	}
}
