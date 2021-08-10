package com.example.attsk.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.attsk.model.*;
import com.example.attsk.service.*;

@RestController
public class UsersController 
{
	
	//add IUsersService
	@Autowired
	IUsersService usersServiceRef;
	
	@GetMapping("/users")
	public List<UsersDto> getAllUsers()
	{
		return usersServiceRef.getAllUsers();
	}
	
	@GetMapping("/users/{userId}")
	public UsersDto getUserById(@PathVariable int userId)
	{
		return usersServiceRef.getUserById(userId);
		
	}

}
