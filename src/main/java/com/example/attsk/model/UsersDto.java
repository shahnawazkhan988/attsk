package com.example.attsk.model;

import java.util.*;

import javax.persistence.*;

@Entity
public class UsersDto 
{
	@Id
	@GeneratedValue
	private int id;
	private String userName;	
	private String userMatricola;
	private String userPass;
	private String userRole;
	
	
	
	
	public UsersDto(int id, String userName, String userMatricola, String userPass, String userRole) {
		super();
		this.id = id;
		this.userName = userName;
		this.userMatricola = userMatricola;
		this.userPass = userPass;
		this.userRole = userRole;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMatricola() {
		return userMatricola;
	}
	public void setUserMatricola(String userMatricola) {
		this.userMatricola = userMatricola;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	

	@Override
	public String toString() {
		return "UsersDto [id=" + id + ", userName=" + userName + ", userMatricola=" + userMatricola + ", userPass="
				+ userPass + ", userRole=" + userRole + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, userMatricola, userName, userPass, userRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersDto other = (UsersDto) obj;
		
		return Objects.equals(id, other.id) && Objects.equals(userMatricola, other.userMatricola)&& Objects.equals(userName, other.userName)&& Objects.equals(userPass, other.userPass) && userRole == other.userRole;
	}
	
	
	
	
}
