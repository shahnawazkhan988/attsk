package com.example.attsk.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.example.attsk.dao.*;
import com.example.attsk.model.*;

@Service
public class UsersServiceImpl implements IUsersService{
	
	@Autowired
	private IUsersDao userDaoRef;

	@Override
	public List<UsersDto> getAllUsers() {
		// TODO Auto-generated method stub
		return userDaoRef.findAll();
	}

	@Override
	public UsersDto getUserById(int userId) {
		// TODO Auto-generated method stub
		return userDaoRef.findById(userId)
				.orElse(null);
	}

}
