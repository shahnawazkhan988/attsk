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
		
		return userDaoRef.findAll();
	}

	
	public UsersDto getUserById(long userId) {
		
		return userDaoRef.findById(userId)
				.orElse(null);
	}


	public UsersDto createNewUser(UsersDto users) {
		
		try 
		{
			return userDaoRef.save(users);
		} 
		catch (Exception exception) 
		{
			
			throw new UserIdExceptions("Matricola number :"+users.getUserMatricola()+" Already Exists");
		}
		
	}


	public void deleteUser(String matricola) {	

		var userDao = userDaoRef.findByuserMatricola(matricola);
        if(userDao!=null){
        	userDaoRef.delete(userDao);
        }else {
            throw new UserIdExceptions("User with matricola :"+matricola+" does not Exists");
        }
	}
	

}
