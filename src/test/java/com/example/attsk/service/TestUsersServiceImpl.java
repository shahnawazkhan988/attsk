package com.example.attsk.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.mockito.quality.*;

import com.example.attsk.dao.*;
import com.example.attsk.model.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TestUsersServiceImpl {
	
	@Mock
	private IUsersDao iUsersDao;
	
	@InjectMocks
	private UsersServiceImpl usersServiceImpl;
	
	@Test
	public void test_getAllUsers()
	{	
		UsersDto user1 = new UsersDto(1, "Shahnawaz", "70001", "123456", "ST");
		UsersDto user2 = new UsersDto(2, "User2", "70002", "123456", "ST");
		when(iUsersDao.findAll()).thenReturn(new ArrayList<>(Arrays.asList(user1, user2)));
		assertThat(usersServiceImpl.getAllUsers()).
		containsExactly(user1, user2);
	}
	
	@Test
	public void test_getUserById_found()
	{
		UsersDto user = new UsersDto(1, "Shahnawaz", "70001", "123456", "ST");
		when(iUsersDao.findById(1)).thenReturn(Optional.of(user));
		assertThat(usersServiceImpl.getUserById(1)).isSameAs(user);
	}
	
	@Test
	public void test_getUserById_notFound()
	{		
		when(iUsersDao.findById(anyInt()))
		.thenReturn(Optional.empty());
		assertThat(usersServiceImpl.getUserById(1))
		.isNull();
	}

}
