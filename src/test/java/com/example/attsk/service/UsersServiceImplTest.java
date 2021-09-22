package com.example.attsk.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
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
class UsersServiceImplTest {
	
	
	@Mock
	private IUsersDao iUsersDao;

	@InjectMocks
	private UsersServiceImpl usersServiceImpl;

	@Test
	void test_getAllUsers() { // given
		UsersDto user1 = new UsersDto();
		UsersDto user2 = new UsersDto();
		user1.setId(1L);
		user1.setUserName("Shahnawaz");
		user1.setUserMatricola("70001");
		user1.setUserPass("123456");
		user1.setUserRole("ST");

		user2.setId(1L);
		user2.setUserName("User2");
		user2.setUserMatricola("70002");
		user2.setUserPass("123456");
		user2.setUserRole("ST");

		// when
		when(iUsersDao.findAll())

				// then
				.thenReturn(new ArrayList<>(Arrays.asList(user1, user2)));

		// Assert
		assertThat(usersServiceImpl.getAllUsers()).containsExactly(user1, user2);
	}

	@Test
	void test_getUserById_found() {
		// given
		UsersDto user = new UsersDto();
		user.setId(1L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");

		// when
		when(iUsersDao.findById(1L))

				// then
				.thenReturn(Optional.of(user));

		// Assert
		assertThat(usersServiceImpl.getUserById(1)).isSameAs(user);
	}

	@Test
	void test_getUserById_notFound() {
		// when
		when(iUsersDao.findById(anyLong()))

				// then
				.thenReturn(Optional.empty());

		// Assert
		assertThat(usersServiceImpl.getUserById(1)).isNull();
	}

	@Test
	void test_addUsers() throws Exception {
		// given
		UsersDto users = new UsersDto();
		users.setId(1L);
		users.setUserName("Shahnawaz");
		users.setUserMatricola("70001");
		users.setUserPass("123456");
		users.setUserRole("ST");

		// when
		when(usersServiceImpl.createNewUser(users))

				// then
				.thenReturn(users);

		// assert
		assertEquals(users, usersServiceImpl.createNewUser(users));

	}
	

	
//	@Test
//    void test_addUsers_exception(){
//		// given
//		UsersDto users = new UsersDto();
//		users.setId(1L);
//		users.setUserName("Shahnawaz");
//		users.setUserMatricola("70001");
//		users.setUserPass("123456");
//		users.setUserRole("ST");
//		// when
//			when(usersServiceImpl.createNewUser(users)).thenReturn(null);
////        Then
//        assertThrows(DuplicateUserExceptions.class,()->usersServiceImpl.createNewUser(users));
//    }
	
//	@Test
//	void test_addUsers_exception(){
//		// given
//		UsersDto users = new UsersDto();
//		users.setId(1L);
//		users.setUserName("Shahnawaz");
//		users.setUserMatricola("70001");
//		users.setUserPass("123456");
//		users.setUserRole("ST");
//
//		// when
//		when(usersServiceImpl.createNewUser(users))
//
//				// then
//				.thenThrow(new RuntimeException("error"));
//		
//		// assert
//		assertEquals( HttpStatus.BAD_REQUEST,   users, usersServiceImpl.createNewUser(users));
//
//	}

	@Test
	void test_deleteUser() throws Exception{
		// given
		UsersDto users = new UsersDto();
		users.setId(1L);
		users.setUserName("Shahnawaz");
		users.setUserMatricola("70001");
		users.setUserPass("123456");
		users.setUserRole("ST");

		given(iUsersDao.findByuserMatricola(any())).willReturn(users);
		// When
		usersServiceImpl.deleteUser(users.getUserMatricola());

		// then
		then(iUsersDao).should().delete(users);
		then(iUsersDao).shouldHaveNoMoreInteractions();
		// verify
		verify(iUsersDao, times(1)).delete(users);
	}

}
