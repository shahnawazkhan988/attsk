package com.example.attsk.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Data
public class UsersDto 
{
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "User Name is required")
	private String userName;
	
	@NotNull(message = "{validation.userMatricola.NotNull}")
    @Positive(message = "{validation.userMatricola.Positive}")
	private String userMatricola;
	@NotBlank(message = "User userPass is required")
	private String userPass;
	private String userRole;	
	
}
