package com.example.attsk.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
public class UsersDto 
{
	@Id
	@GeneratedValue
	private int id;
	private String userName;	
	private String userMatricola;
	private String userPass;
	private String userRole;	
	
}
