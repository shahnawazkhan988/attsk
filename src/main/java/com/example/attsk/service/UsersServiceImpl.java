package com.example.attsk.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.attsk.dao.*;
import com.example.attsk.exceptions.*;
import com.example.attsk.model.*;

@Service
public class UsersServiceImpl {
	
	@Autowired
	private IUsersDao userDaoRef;

	
	public List<UsersDto> getAllUsers() {
		// TODO Auto-generated method stub
		return userDaoRef.findAll();
	}

	
	public UsersDto getUserById(long userId) {
		// TODO Auto-generated method stub
		return userDaoRef.findById(userId)
				.orElse(null);
	}


	public UsersDto createNewUser(UsersDto users) {
		// TODO Auto-generated method stub
		try 
		{
			return userDaoRef.save(users);
		} 
		catch (Exception exception) 
		{
			// TODO: handle exception
			throw new DuplicateUserExceptions("Matricola number :"+users.getUserMatricola()+" Already Exists");
		}
		
	}


	public void deleteUser(UsersDto users) {
		// TODO Auto-generated method stub
		userDaoRef.delete(users);
	}


	public UsersDto getUserByMatricola(String matricola) {
		// TODO Auto-generated method stub
		return null;
	}


	



	

}
