package com.example.demo.form;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class MakerUpdateForm implements Serializable{
	
	
	private Long id;

	@NotBlank
	private String makerName;
    
	
	private Date createdAt;
	
	
    private Date updatedAt;

}
