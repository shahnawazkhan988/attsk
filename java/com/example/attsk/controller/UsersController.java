package com.example.attsk.controller;

import java.util.*;

import javax.validation.*;

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
public class UsersController {

	// add IUsersService
	@Autowired
	UsersServiceImpl usersServiceRef;

	public UsersController(UsersServiceImpl usersServiceImpl) {
		
		this.usersServiceRef = usersServiceImpl;
	}

	@GetMapping("/users")
	public List<UsersDto> getAllUsers() {
		return usersServiceRef.getAllUsers();
	}

	@GetMapping("/users/{userId}")
	public UsersDto getUserById(@PathVariable long userId) {
		return usersServiceRef.getUserById(userId);

	}


	@RequestMapping(value = "/new/users", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Object> createNewUser(@Valid @RequestBody UsersDto users, BindingResult bindingResult) {
		

		if (bindingResult.hasErrors()) {
			log.error("Problem with body content in while create new user");
			Map<String, String> errorsMap = new HashMap<>();
			bindingResult.getFieldErrors()
					.forEach(fieldError -> errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage()));
			return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
		}
		log.debug("User has been created successfully " + users.getUserMatricola());
		return new ResponseEntity<>(usersServiceRef.createNewUser(users), HttpStatus.CREATED);
	}

	@DeleteMapping("/{matricola}")
	public ResponseEntity<Object>  deleteUser(@PathVariable String matricola) {
		try 
		{
			usersServiceRef.deleteUser(matricola);
			log.debug("Deleting the user with Matricola:"+matricola);
            return new ResponseEntity<>("User with Matricola: "+matricola+" has been deleted successfully",HttpStatus.OK);			
		} 
		catch (Exception e) 
		{
			 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
