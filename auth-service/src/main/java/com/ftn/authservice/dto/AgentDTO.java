package com.ftn.authservice.dto;

import javax.persistence.OneToOne;

import com.ftn.authservice.model.AccommodationObject;
import com.ftn.authservice.model.Agent;
import com.ftn.authservice.model.User;

public class AgentDTO extends UserDTO{
	
	private String pib;
	
	private AccommodationObjectDTO accObj;

	public AgentDTO(String pib, AccommodationObject accObj) {
		super();
		this.pib = pib;
		this.accObj = new AccommodationObjectDTO(accObj.getId(), accObj.getName(), accObj.getAddressId(), accObj.getDescription(), accObj.getCategoryId(), accObj.isFreeCancelation(), accObj.getDaysToCancel(), accObj.getTypeId());
	}
	
	

	public AgentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgentDTO(Agent a) {
		this.pib = a.getPib();
		this.setAddress(a.getAddressId());
		this.setEmail(a.getEmail());
		this.setEnabled(a.isEnabled());
		this.setId(a.getId());
		this.setName(a.getName());
		this.setNonLocked(a.isNonLocked());
		this.setPassword(a.getPassword());
		this.setSurname(a.getSurname());
		this.accObj = new AccommodationObjectDTO(a.getAccObj().getId(), a.getAccObj().getName(), a.getAccObj().getAddressId(), a.getAccObj().getDescription(), a.getAccObj().getCategoryId(), a.getAccObj().isFreeCancelation(), a.getAccObj().getDaysToCancel(), a.getAccObj().getTypeId());
	}

	public AgentDTO(Long id, String name, String surname, Long address, String email, String password, boolean enabled,
			boolean nonLocked) {
		super(id, name, surname, address, email, password, enabled, nonLocked);
		// TODO Auto-generated constructor stub
	}



	public AgentDTO(User u) {
		super(u);
		// TODO Auto-generated constructor stub
	}



	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public AccommodationObjectDTO getAccObj() {
		return accObj;
	}

	public void setAccObjDTO(AccommodationObjectDTO accObj) {
		this.accObj = accObj;
	} 
	
	
}
