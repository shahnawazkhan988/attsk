package com.example.attsk.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.example.attsk.dao.*;
import com.example.attsk.model.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
public class TestUsersServiceImpl {
	
	@Mock
	private IUsersDao iUsersDao;
	
	@InjectMocks
	private UsersServiceImpl usersServiceImpl;
	
	@Test
	public void test_getAllUsers() 
	{	//given
		UsersDto user1 = new UsersDto();
		UsersDto user2 = new UsersDto();
		user1.setId(1);
		user1.setUserName("Shahnawaz");
		user1.setUserMatricola("70001");
		user1.setUserPass("123456");
		user1.setUserRole("ST");
		
		user2.setId(1);
		user2.setUserName("User2");
		user2.setUserMatricola("70002");
		user2.setUserPass("123456");
		user2.setUserRole("ST");
				
		//when
		when(iUsersDao.findAll())
		
		//then
		.thenReturn(new ArrayList<>(Arrays.asList(user1, user2)));
				
		//Assert
		assertThat(usersServiceImpl.getAllUsers()).
		containsExactly(user1, user2);
	}
	
	@Test
	public void test_getUserById_found()
	{
		//given
		UsersDto user = new UsersDto();
		user.setId(1);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");
		
		//when
		when(iUsersDao.findById(1))
		
		//then
		.thenReturn(Optional.of(user));
			
		//Assert
		assertThat(usersServiceImpl.getUserById(1)).isSameAs(user);
	}
	
	@Test
	public void test_getUserById_notFound()
	{	
		//when	
		when(iUsersDao.findById(anyInt()))
		
		//then
		.thenReturn(Optional.empty());
		
		//Assert
		assertThat(usersServiceImpl.getUserById(1))
		.isNull();
	}
	
	@Test
	public void test_addUsers()
	{
		//given
		UsersDto users = new UsersDto();
		users.setId(1);
		users.setUserName("Shahnawaz");
		users.setUserMatricola("70001");
		users.setUserPass("123456");
		users.setUserRole("ST");
		
		//when
		when(usersServiceImpl.createNewUser(users))
		
		//then
		.thenReturn(users);	
		
		//assert
		assertEquals(users, usersServiceImpl.createNewUser(users));
		
	}
	
	@Test
	public void test_deleteUser()
	{
		//given
		UsersDto users = new UsersDto();
		users.setId(1);
		users.setUserName("Shahnawaz");
		users.setUserMatricola("70001");
		users.setUserPass("123456");
		users.setUserRole("ST");
		
		//delete
		usersServiceImpl.deleteUser(users);
		
		//verify
		verify(iUsersDao, times(1)).delete(users);
	}

}
