package com.example.attsk.service;

import java.util.*;

import com.example.attsk.model.*;

public interface IUsersService {

	List<UsersDto> getAllUsers();
	UsersDto getUserById(long userId);
	UsersDto createNewUser(UsersDto users);
	void deleteUser(String matricola);
	UsersDto updateUser(long userId, UsersDto usersDto);

}
