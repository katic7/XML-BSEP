package com.ftn.authservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;

@Data
@Entity
public class Image {
	
	@Id
    @Column(name = "id")
    private Long id;
  
    @Column(name = "name")
    private String name;
    
    @Column(name = "type")
    private String type;
  
    @Lob
    @Column(name="pic")
    private byte[] pic;
    
    public Image(long id, String name, String type, byte[] pic){
        this.id = id;
        this.name = name;
        this.type = type;
        this.pic = pic;
    }
    
    public Image() {
    	
    }
		
}
