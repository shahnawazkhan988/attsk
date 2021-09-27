package com.example.attsk.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.attsk.domain.Users;
import com.example.attsk.model.UsersDto;

@Mapper
public interface UsersMapper 
{
	UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);
	
	UsersDto usersToUsersDto(Users users);
	Users usersDtoToUsers(UsersDto usersDto);
}
