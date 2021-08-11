package com.example.attsk.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.attsk.model.*;
import com.example.attsk.service.*;

@RestController
@RequestMapping("/api/v1/")
public class UsersController 
{
	
	//add IUsersService
	@Autowired
	UsersServiceImpl usersServiceRef;
	
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
	
	@PostMapping("/users")
	public UsersDto createNewUser(@RequestBody UsersDto users)
	{
		return usersServiceRef.createNewUser(users);
	}
	
	@DeleteMapping("/users")
	public UsersDto deleteUser(@RequestBody UsersDto users)
	{
		usersServiceRef.deleteUser(users);
		return users;
	}

}
