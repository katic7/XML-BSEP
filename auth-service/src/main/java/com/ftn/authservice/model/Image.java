package com.ftn.authservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;


	@Lob
	@Column(name = "data")
	private byte[] data;
	
	@ManyToOne
    @JoinColumn
    protected AccommodationUnit accUnit;

	public Image() {
	}

	public Image(Long id, String name, byte[] data, AccommodationUnit accUnit) {
		super();
		this.id = id;
		this.name = name;
		this.data = data;
		this.accUnit = accUnit;
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

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public AccommodationUnit getAccUnit() {
		return accUnit;
	}

	public void setAccUnit(AccommodationUnit accUnit) {
		this.accUnit = accUnit;
	}

}
