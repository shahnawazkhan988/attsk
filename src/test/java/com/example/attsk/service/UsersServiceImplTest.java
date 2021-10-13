package com.example.attsk.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import com.example.attsk.dao.*;
import com.example.attsk.entities.Users;
import com.example.attsk.exceptions.*;
import com.example.attsk.model.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {

	@Mock
	private IUsersDao iUsersDao;

	@InjectMocks
	private UsersServiceImpl usersServiceImpl;

	@Test
	void test_getAllUsers() { // given
		Users user1 = new Users();
		Users user2 = new Users();
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

		UsersDto usersDto = new UsersDto();
		usersDto.setId(user1.getId());
		usersDto.setUserName(user1.getUserName());
		usersDto.setUserMatricola(user1.getUserMatricola());
		usersDto.setUserPass(user1.getUserPass());
		usersDto.setUserRole(user1.getUserRole());

		UsersDto usersDto2 = new UsersDto();
		usersDto2.setId(user2.getId());
		usersDto2.setUserName(user2.getUserName());
		usersDto2.setUserMatricola(user2.getUserMatricola());
		usersDto2.setUserPass(user2.getUserPass());
		usersDto2.setUserRole(user2.getUserRole());

		// when
		when(iUsersDao.findAll())

				// then
				.thenReturn(new ArrayList<>(Arrays.asList(user1, user2)));

		// Assert
		assertThat(usersServiceImpl.getAllUsers()).containsExactly(usersDto, usersDto2);
	}

	@Test
	void test_getUserById_found() {
		// given
		Users user = new Users();
		user.setId(1L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");

		UsersDto usersDto = new UsersDto();
		usersDto.setId(user.getId());
		usersDto.setUserName(user.getUserName());
		usersDto.setUserMatricola(user.getUserMatricola());
		usersDto.setUserPass(user.getUserPass());
		usersDto.setUserRole(user.getUserRole());
		// When
		when(iUsersDao.findById(1L)).thenReturn(Optional.of(user));

		UsersDto usersDto1 = usersServiceImpl.getUserById(1L);
		// Then
		assertNotNull(usersDto1);
		assertEquals(1L, usersDto1.getId());
		assertEquals("Shahnawaz", usersDto1.getUserName());
		then(iUsersDao).shouldHaveNoMoreInteractions();
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
		Users users = new Users();
		users.setId(1L);
		users.setUserName("Shahnawaz");
		users.setUserMatricola("70001");
		users.setUserPass("123456");
		users.setUserRole("ST");

		UsersDto usersDto = new UsersDto();
		usersDto.setId(users.getId());
		usersDto.setUserName(users.getUserName());
		usersDto.setUserMatricola(users.getUserMatricola());
		usersDto.setUserPass(users.getUserPass());
		usersDto.setUserRole(users.getUserRole());

		given(iUsersDao.save(any(Users.class))).willReturn(users);
		// When
		UsersDto usersDto1 = usersServiceImpl.createNewUser(usersDto);

		// then
		assertNotNull(usersDto1);
		assertEquals(1L, usersDto1.getId());
		assertEquals("Shahnawaz", usersDto1.getUserName());
		assertEquals("70001", usersDto1.getUserMatricola());
		assertEquals("123456", usersDto1.getUserPass());
		assertEquals("ST", usersDto1.getUserRole());
		assertNotNull(usersDto1.getId());

	}

	@Test
	void test_addUsers_exception() {
		// given
		Users users = new Users();
		users.setId(1L);
		users.setUserName("Shahnawaz");
		users.setUserMatricola("70001");
		users.setUserPass("123456");
		users.setUserRole("ST");

		UsersDto usersDto = new UsersDto();
		usersDto.setId(users.getId());
		usersDto.setUserName(users.getUserName());
		usersDto.setUserMatricola(users.getUserMatricola());
		usersDto.setUserPass(users.getUserPass());
		usersDto.setUserRole(users.getUserRole());

		// when
		given(iUsersDao.save(any(Users.class))).willThrow(UserIdExceptions.class);
		// then
		assertThrows(UserIdExceptions.class, () -> usersServiceImpl.createNewUser(usersDto));
	}

	@Test
	void test_deleteUser() throws Exception {
		// given
		Users users = new Users();
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

	@Test
	void test_deleteUserThrowsProIdExp() {
		// given
		Users users = new Users();
		users.setId(1L);
		users.setUserMatricola("70001");
		given(iUsersDao.findByuserMatricola(any())).willReturn(nullable(Users.class));

		// then
		assertThrows(UserIdExceptions.class, () -> usersServiceImpl.deleteUser("70001"));
	}

	@Test
	void test_UserUpdate() {
		// given
		UsersDto usersDto = new UsersDto();
		usersDto.setId(1L);
		usersDto.setUserName("Shahnawaz");
		usersDto.setUserMatricola("70001");
		usersDto.setUserPass("123456");
		usersDto.setUserRole("ST");

		Users users = new Users();
		users.setId(usersDto.getId());
		users.setUserName(usersDto.getUserName());
		users.setUserMatricola(usersDto.getUserMatricola());
		users.setUserPass(usersDto.getUserPass());
		users.setUserRole(usersDto.getUserRole());

		Users saveUsers = new Users();
		saveUsers.setId(1L);
		saveUsers.setUserName(usersDto.getUserName());
		saveUsers.setUserMatricola(usersDto.getUserMatricola());
		saveUsers.setUserPass(usersDto.getUserPass());
		saveUsers.setUserRole(usersDto.getUserRole());

		given(iUsersDao.findById(anyLong())).willReturn(Optional.of(users));
		given(iUsersDao.save(any(Users.class))).willReturn(saveUsers);

		// When
		UsersDto usersDto1 = usersServiceImpl.updateUser(1L, usersDto);
		// Then
		assertEquals(usersDto1.getUserName(), saveUsers.getUserName());
		assertEquals(usersDto1.getId(), saveUsers.getId());
		assertNotNull(usersDto1.getId());
		assertNotNull(usersDto1.getUserMatricola());
		assertEquals(usersDto1.getUserPass(), saveUsers.getUserPass());
		assertEquals(saveUsers.getUserMatricola(), usersDto1.getUserMatricola());

		then(iUsersDao).should().save(any(Users.class));
		then(iUsersDao).shouldHaveNoMoreInteractions();
		then(iUsersDao).shouldHaveNoMoreInteractions();

	}

	@Test
	void test_UserUpdateThrows() {
		UsersDto usersDto = new UsersDto();
		assertThrows(UserNotFoundException.class, () -> usersServiceImpl.updateUser(1L, usersDto));
	}
	
	
	@Test
	void test_UserUpdateThrowsUserIdExceptions() {
		// given
		UsersDto usersDto = new UsersDto();
		usersDto.setUserMatricola("70001");

		Users users = new Users();
		users.setUserMatricola("7002");
		given(iUsersDao.findById(anyLong())).willReturn(Optional.of(users));
		
		//then
		assertThrows(UserIdExceptions.class,()->usersServiceImpl.updateUser(1L, usersDto));
		

	}	
}
