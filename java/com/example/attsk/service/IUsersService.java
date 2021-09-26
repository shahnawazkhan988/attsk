package com.example.attsk.service;

import java.util.*;

import com.example.attsk.model.*;

public interface IUsersService {

	List<UsersDto> getAllUsers();

	UsersDto getUserById(int userId);

}
