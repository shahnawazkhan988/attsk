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
	private Long id;
	@NotBlank(message = "User Name is required")
	private String userName;
	@NotNull(message = "userMatricola is required")
	@Size(min = 4,max = 5, message = "Please use 4 to 5 characters long")
	@Column(updatable = false,unique = true)
	private String userMatricola;
	@NotBlank(message = "User userPass is required")
	private String userPass;
	private String userRole;	
	
}
