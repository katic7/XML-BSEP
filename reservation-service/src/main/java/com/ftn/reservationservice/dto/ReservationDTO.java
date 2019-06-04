package com.ftn.reservationservice.dto;

import java.util.Date;

public class ReservationDTO {
	
	private Date beginDate;
	private Date endDate;
	private float price;
	private Long accommodationUnitId;
	private Long userId;
	
	public ReservationDTO() {
		super();
	}

	public ReservationDTO(Date startDate, Date endDate, float price, Long accommodationUnitId, Long userId) {
		super();
		this.beginDate = startDate;
		this.endDate = endDate;
		this.price = price;
		this.accommodationUnitId = accommodationUnitId;
		this.userId = userId;
	}

	

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Long getAccommodationUnitId() {
		return accommodationUnitId;
	}

	public void setAccommodationUnitId(Long accommodationUnitId) {
		this.accommodationUnitId = accommodationUnitId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	

}
