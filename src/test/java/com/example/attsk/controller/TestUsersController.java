package com.example.attsk.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.example.attsk.model.*;
import com.example.attsk.service.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class TestUsersController 
{
	@InjectMocks 
	private UsersController usersController;
	
	@Mock
	private UsersServiceImpl usersServiceImpl;
	
	@Test
	public void test_getAllUsers()
	{
		
		//given
		List<UsersDto> users = new ArrayList<UsersDto>();
		users.add(new UsersDto(1, "Shahnawaz", "70001", "123456", "ST"));
		users.add(new UsersDto(2, "User2", "70002", "123456", "ST"));
		
		//when
		when(usersServiceImpl.getAllUsers())
		
		//then
		.thenReturn(users);
		
		//Assert
		List<UsersDto> users2 = usersController.getAllUsers();
		assertEquals(2, users2.size());
		
		//verify
		verify(usersServiceImpl, times(1)).getAllUsers();
		
	}
	
	
//	@Test
//	public void test_getUserById_found()
//	{
//		//given
//		Integer id = 1;
//		
//		UsersDto user = new UsersDto(1, "Shahnawaz", "70001", "123456", "ST");
//		
//		//when
//		when(usersServiceImpl.getUserById(id))
//		
//		//then
//		.thenReturn(user);
//			
//		//Assert
//		//assertThat(1, user.);
//		UsersDto users2 = usersController.getUserById(id);
//		assertEquals(1, users2);
//	}
//	
//	@Test
//	public void test_getUserById_found()
//	{
//		//when
////		when(usersServiceImpl.getUserById(anyInt()))
////		
////		//then
////		.thenReturn(new UsersDto(1, "Shahnawaz", "70001", "123456", "ST"));
////			
//		//Assert
//		//assertThat(1, user.);
//		//UsersDto users2 = usersController.getUserById(id);
//		//assertEquals(1, );
//		
//		//given
//		Integer id = 1;
//		
//		UsersDto users = usersServiceImpl.getUserById(id);
//		
//		assertThat(users.getId()).isEqualTo(id);
//	}
	
	
}
