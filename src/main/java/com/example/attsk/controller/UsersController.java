package com.example.attsk.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;

import com.example.attsk.model.*;
import com.example.attsk.service.*;

import lombok.extern.slf4j.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/")
public class UsersController 
{
	
	//add IUsersService
	@Autowired
	UsersServiceImpl usersServiceRef;
	
	public UsersController(UsersServiceImpl usersServiceImpl) {
		// TODO Auto-generated constructor stub
		this.usersServiceRef = usersServiceImpl;
	}

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
	public ResponseEntity<Object> createNewUser(@RequestBody UsersDto users, BindingResult bindingResult)
	{
		
        if(bindingResult.hasErrors())
        {
        	log.error("Problem with body content in while create new user");
        	Map<String, String> errorsMap = new HashMap<>();
        	bindingResult.getFieldErrors().forEach(fieldError -> errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
        	return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST); 
        }
        log.debug(""+users.getUserName().toUpperCase());
		return new ResponseEntity<>(usersServiceRef.createNewUser(users), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/users")
	public UsersDto deleteUser(@RequestBody UsersDto users)
	{
		usersServiceRef.deleteUser(users);
		return users;
	}

}
