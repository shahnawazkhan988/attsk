package com.example.attsk.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.*;

import com.example.attsk.dao.*;
import com.example.attsk.entities.Users;
import com.example.attsk.exceptions.*;
import com.example.attsk.mapper.UsersMapper;
import com.example.attsk.model.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl {
	
	//@Autowired
	private final IUsersDao userDaoRef;
	private static final UsersMapper usersMapper = UsersMapper.INSTANCE;
	
	public List<UsersDto> getAllUsers() {
		
		var user = userDaoRef.findAll();
		return usersMapper.toUserDto(user);
	}

	
	public UsersDto getUserById(long userId) {
		
		var user = userDaoRef.findById(userId).orElse(null);
		return usersMapper.usersToUsersDto(user);
				
	}


	public UsersDto createNewUser(UsersDto users) {
		
		try 
		{
			Users detachedUsers = usersMapper.usersDtoToUsers(users);
			
			Users savedUsers = userDaoRef.save(detachedUsers);
            return  usersMapper.usersToUsersDto(savedUsers);
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


	public UsersDto updateUser(long userId, UsersDto usersDto) {
		Optional<Users> users = userDaoRef.findById(userId);
		if(users.isPresent())
		{
			var users1 = users.get();
			if(!(users1.getUserMatricola().equalsIgnoreCase(usersDto.getUserMatricola())))
			{
				throw new UserIdExceptions("User matricola is not updatable, Original: "+users1.getUserMatricola()+" Updating: "+usersDto.getUserMatricola());
			}
		}
		else
		{
			throw new UserNotFoundException("User with matricola :"+userId+" does not Exists");
		}
		
		var detachedUsers = usersMapper.usersDtoToUsers(usersDto);
		detachedUsers.setId(userId);
		detachedUsers.setUserMatricola(detachedUsers.getUserMatricola().toUpperCase());
        var savedUser = userDaoRef.save(detachedUsers);

        return usersMapper.usersToUsersDto(savedUser);
	}
	

}
