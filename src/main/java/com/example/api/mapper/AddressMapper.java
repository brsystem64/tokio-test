package com.example.api.mapper;

import com.example.api.domain.Address;
import com.example.api.dto.AddressDto;
import com.example.api.dto.CepDto;

public class AddressMapper implements IMapper<Address, CepDto> {


	@Override
	public Address dtoToEntity(CepDto dto) {
	     Address address = new Address();    
		 address.setCep(dto.getCep());
	     address.setStreet(dto.getLogradouro());
	     address.setDistrict(dto.getBairro());
	     address.setCity(dto.getLocalidade());
	     address.setState(dto.getUf());
	     
		return address;
	}





}
