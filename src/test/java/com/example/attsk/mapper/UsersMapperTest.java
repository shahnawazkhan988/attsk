package com.example.attsk.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.attsk.entities.Users;
import com.example.attsk.model.UsersDto;

public class UsersMapperTest {
	public static final Long ID = 1L;
	public static final String USER_NAME = "userName";
	public static final String USER_Matricola = "70001";
	public static final String USER_Pass = "userPAss";
	public static final String USER_Role = "ST";

	UsersMapper usersMapper = UsersMapper.INSTANCE;

	@Test
	void usersToUsersDtoReturnsNull() {
		assertNull(usersMapper.usersToUsersDto(null));
	}

	@Test
	void usersToUsersDtoReturnsEmptyObject() {
		assertNotNull(usersMapper.usersToUsersDto(new Users()));
	}

	@Test
	void usersToUsersDto() {
		// given
		Users user = new Users();
		user.setId(ID);
		user.setUserName(USER_NAME);
		user.setUserMatricola(USER_Matricola);
		user.setUserPass(USER_Pass);
		user.setUserRole(USER_Role);
		
		//when
		UsersDto usersDto = usersMapper.usersToUsersDto(user);
		
		//then
		assertNotNull(usersDto);
		assertEquals(ID, usersDto.getId());
		assertEquals(USER_NAME, usersDto.getUserName());
		assertEquals(USER_Matricola,usersDto.getUserMatricola());
		assertEquals(USER_Pass,usersDto.getUserPass());
		assertEquals(USER_Role,usersDto.getUserRole());
		
	}
	
	@Test
	void usersDtoToUsersReturnsNull() {
		assertNull(usersMapper.usersDtoToUsers(null));
	}

	@Test
	void usersDtoToUsersReturnsEmptyObject() {
		assertNotNull(usersMapper.usersDtoToUsers(new UsersDto()));
	}

	@Test
	void usersDtoToUsers() {
		// given
		UsersDto usersDto = new UsersDto();
		usersDto.setId(ID);
		usersDto.setUserName(USER_NAME);
		usersDto.setUserMatricola(USER_Matricola);
		usersDto.setUserPass(USER_Pass);
		usersDto.setUserRole(USER_Role);
		
		//when
		Users users = usersMapper.usersDtoToUsers(usersDto);
		
		//then
		assertNotNull(users);
		assertEquals(ID, users.getId());
		assertEquals(USER_NAME, users.getUserName());
		assertEquals(USER_Matricola,users.getUserMatricola());
		assertEquals(USER_Pass,users.getUserPass());
		assertEquals(USER_Role,users.getUserRole());
		
	}
	
	@Test
	void toUserDtoReturnsNull() {
		assertNull(usersMapper.toUserDto(null));
	}

//	@Test
//	void toUserDtoReturnsEmptyObject() {
//		users = new List<Users> users;
//		
//		assertNotNull(usersMapper.toUserDto( new List<Users> ));
//	}
	
	@Test
	void toUserDto() {
		// given
		Users user = new Users();
		user.setId(ID);
		user.setUserName(USER_NAME);
		user.setUserMatricola(USER_Matricola);
		user.setUserPass(USER_Pass);
		user.setUserRole(USER_Role);
		
		List<Users> user2 =new ArrayList<>(Arrays.asList(user));
		//when
		List<UsersDto> usersDto = usersMapper.toUserDto(user2);
		
		//then
		assertNotNull(usersDto);
		
	}
}
