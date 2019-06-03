package com.ftn.accommodationservice.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
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
	

}


