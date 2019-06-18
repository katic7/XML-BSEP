package com.ftn.agentservice.dto;

import java.math.BigInteger;


public class AccommodationObjectDTO {
	private Long id;
	private String name;
	private Long addressId;
	private String description;
	private Long categoryId;
	private boolean freeCacelation;
	private BigInteger daystoCancel;
	private Long typeId;

	public AccommodationObjectDTO(Long id,String name, Long address,String description,Long category, boolean cancel, BigInteger daysToCancel, Long typeId) {
		this.id = id;
		this.name = name;
		this.addressId = address;
		this.description = description;
		this.categoryId = category;
		this.freeCacelation = cancel;
		this.daystoCancel = daysToCancel;
		this.typeId = typeId;
		
	}

	public AccommodationObjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isFreeCacelation() {
		return freeCacelation;
	}

	public void setFreeCacelation(boolean freeCacelation) {
		this.freeCacelation = freeCacelation;
	}

	public BigInteger getDaystoCancel() {
		return daystoCancel;
	}

	public void setDaystoCancel(BigInteger daystoCancel) {
		this.daystoCancel = daystoCancel;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	

}


