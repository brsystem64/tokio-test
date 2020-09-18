package com.example.api.mapper;

public interface IMapper<T, D> {
	
	public T dtoToEntity(D dto);

}
