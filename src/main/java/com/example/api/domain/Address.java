package com.example.api.domain;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotEmpty
	private String cep;

	/*
	 * Cidades Pequenas utilizam o mesmo cep para toda as ruas
	 * Portanto não inserir @NotBlank, @NotNull
	 */
	@Column
	private String street;

	/*
	 * Cidades Pequenas utilizam o mesmo cep para toda os bairros
	 * Portanto não inserir @NotBlank, @NotNull
	 */
	@Column 
	private String district; 
	
	@Column
	@NotEmpty
	@NotNull
	private String city;

	@Column
	@NotEmpty
	@NotNull
	private String state;

	
	
	@JsonIgnoreProperties("address")
	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "address")
	private List<Customer> customer;
	

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}


	public List<Customer> getCustomer() {
		return customer;
	}



	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}



	public String getCep() {
		return cep;
	}



	public void setCep(String cep) {
		this.cep = cep;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}


	
	
	
	}
