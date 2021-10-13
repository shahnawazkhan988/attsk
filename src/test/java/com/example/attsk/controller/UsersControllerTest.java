package com.example.attsk.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.*;

import org.hamcrest.core.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.validation.*;

import com.example.attsk.exceptions.*;
import com.example.attsk.model.*;
import com.example.attsk.service.*;
import com.fasterxml.jackson.databind.*;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {
	public static final Long ID = 1L;
	public static final String USER_NAME = "userName";
	public static final String USER_Matricola = "70001";
	public static final String USER_Pass = "userPAss";
	public static final String USER_Role = "ST";
	public static final String USER_Matricola1 = "70002";

//	@InjectMocks 
//	private UsersController usersController;
	UsersController usersController;
	@Mock
	private UsersServiceImpl usersServiceImpl;

	@Mock
	BindingResult bindingResult;

	MockMvc mockMvc;

	private final ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		usersController = new UsersController(usersServiceImpl);
		mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
	}
	
	@Test
	void test_createNewUser() throws NoSuchMethodException {
		// given
		UsersDto usersDto = getUsersDto(USER_Matricola);
		given(usersServiceImpl.createNewUser(any(UsersDto.class))).willReturn(usersDto);

		// when
		ResponseEntity<Object> user1 = usersController.createNewUser(usersDto, bindingResult);
		System.out.println(user1);

		// then
		assertNotNull(user1.getBody());

		// assert
		assertEquals(HttpStatus.CREATED, user1.getStatusCode());
		assertEquals(usersDto.getClass().getDeclaredMethod("getUserMatricola"),
				Objects.requireNonNull(user1.getBody()).getClass().getDeclaredMethod("getUserMatricola"));
		then(usersServiceImpl).should().createNewUser(any(UsersDto.class));
		then(usersServiceImpl).shouldHaveNoMoreInteractions();

	}

	@Test
	void test_createNewUserStatusIsOK() throws Exception {

		// given
		UsersDto usersDto = getUsersDto(USER_Matricola1);
		given(usersServiceImpl.createNewUser(any(UsersDto.class))).willReturn(usersDto);

		// When
		mockMvc.perform(post("/api/v1/new/users")
		.contentType(MediaType.APPLICATION_JSON)
		.content(mapper.writeValueAsString(usersDto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.userName", equalTo(USER_NAME)))
				.andExpect(jsonPath("$.userMatricola", equalTo(USER_Matricola1)))
				.andExpect(jsonPath("$.userPass", equalTo(USER_Pass)))
				.andExpect(jsonPath("$.userRole", equalTo(USER_Role)));
	}

	@Test
	void test_createNewUserStatusIs400() throws Exception {
		// given
		UsersDto usersDto = new UsersDto();
		usersDto.setId(ID);
		usersDto.setUserName(USER_NAME);
		usersDto.setUserMatricola("Matr8kkkkkkkkkkk888");
		usersDto.setUserPass(USER_Pass);
		usersDto.setUserRole(USER_Role);

		// When
		mockMvc.perform(post("/api/v1/new/users").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(usersDto))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.userMatricola", Is.is("Please use 4 to 5 characters long")));

	}

	@Test
	void test_createNewUserStatusIs400withEmptyUser() throws Exception {
		// given
		UsersDto usersDto = new UsersDto();
		usersDto.setId(ID);

		// When
		mockMvc.perform(post("/api/v1/new/users").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(usersDto))).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.userName", Is.is("User Name is required")))
				.andExpect(jsonPath("$.userMatricola", Is.is("userMatricola is required")))
				.andExpect(jsonPath("$.userPass", Is.is("User userPass is required")));

	}

	@Test
	void test_getAllUsers() throws Exception {

		// given

		List<UsersDto> users = new ArrayList<UsersDto>();
		UsersDto user = new UsersDto();
		UsersDto user1 = new UsersDto();
		user.setId(1L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");

		user1.setId(2L);
		user1.setUserName("User2");
		user1.setUserMatricola("70002");
		user1.setUserPass("123456");
		user1.setUserRole("ST");

		users.add(user);
		users.add(user1);

		// when
		when(usersServiceImpl.getAllUsers())

				// then
				.thenReturn(users);
		this.mockMvc.perform(get("/api/v1/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].userName", is("Shahnawaz")))
				.andExpect(jsonPath("$[0].userMatricola", is("70001")))
				.andExpect(jsonPath("$[0].userPass", is("123456"))).andExpect(jsonPath("$[0].userRole", is("ST")))
				.andExpect(jsonPath("$[1].id", is(2))).andExpect(jsonPath("$[1].userName", is("User2")))
				.andExpect(jsonPath("$[1].userMatricola", is("70002")))
				.andExpect(jsonPath("$[1].userPass", is("123456"))).andExpect(jsonPath("$[1].userRole", is("ST")));

	}

	@Test
	void test_getUserById_found() throws Exception {
		// given
		UsersDto user = new UsersDto();
		user.setId(1L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");

		// when
		when(usersServiceImpl.getUserById(anyLong()))

				// then
				.thenReturn(user);
		this.mockMvc.perform(get("/api/v1/users/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.userName", is("Shahnawaz")))
				.andExpect(jsonPath("$.userMatricola", is("70001"))).andExpect(jsonPath("$.userPass", is("123456")))
				.andExpect(jsonPath("$.userRole", is("ST")));
	}

	@Test
	void test_getAllUsersEmpty() throws Exception {
		this.mockMvc.perform(get("/api/v1/users").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json("[]"));// its return empty JSON list
	}

	@Test
	void test_getUserByIdNotFound() throws Exception {
		when(usersServiceImpl.getUserById(anyLong())).thenReturn(null);
		this.mockMvc.perform(get("/api/v1/users/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect( content().string("")); // content is empty string
	}

	

	@Test
	void test_deleteUser() {
		// given
		UsersDto users = new UsersDto();
		users.setId(1L);
		users.setUserName(USER_NAME);
		users.setUserMatricola(USER_Matricola1);
		users.setUserPass(USER_Pass);
		users.setUserRole(USER_Role);

		// when
		usersController.deleteUser(users.getUserMatricola());

		// then
		then(usersServiceImpl).should().deleteUser(any(String.class));
		then(usersServiceImpl).shouldHaveNoMoreInteractions();
	}
	
	@Test
	void test_deleteUserStatusOK() throws Exception{
		// given
		UsersDto users = new UsersDto();
		users.setId(ID);
		users.setUserName(USER_NAME);
		users.setUserMatricola(USER_Matricola1);
		users.setUserPass(USER_Pass);
		users.setUserRole(USER_Role);

		// when
	        mockMvc.perform(delete("/api/v1/70001")
	        .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$",equalTo("User with Matricola: 70001 has been deleted successfully")));
	}
	
	@Test
	void test_deleteUserStatus400() throws Exception{
		// given
		UsersDto users = new UsersDto();
		users.setId(ID);
		users.setUserName(USER_NAME);
		users.setUserMatricola(USER_Matricola1);
		users.setUserPass(USER_Pass);
		users.setUserRole(USER_Role);
		 willThrow(UserIdExceptions.class).given(usersServiceImpl).deleteUser(null);
		 //       when
       mockMvc.perform(delete("/api/v1/7")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isBadRequest());
	}
	
	@Test
	void test_UserUpdate()
	{
		//given
		UsersDto users = new UsersDto();
		users.setUserPass(USER_Pass);
		users.setId(ID);
		
		UsersDto returnUsers = new UsersDto();
		returnUsers.setId(users.getId());
		returnUsers.setUserPass(users.getUserPass());
		given(usersServiceImpl.updateUser(anyLong(), any(UsersDto.class))).willReturn(returnUsers);
		
		//when
		var responeEntity = usersController.updateUser(ID, users);
		
		assertEquals(HttpStatus.OK, responeEntity.getStatusCode());
	}
	
	@Test
	void test_UserUpdateStatusIsOk() throws Exception
	{
		//given
		UsersDto users = new UsersDto();
		users.setUserPass(USER_Pass);
		users.setId(ID);
		
		UsersDto returnUsers = new UsersDto();
		returnUsers.setId(users.getId());
		returnUsers.setUserPass(users.getUserPass());
		given(usersServiceImpl.updateUser(anyLong(), any(UsersDto.class))).willReturn(returnUsers);
		
		//then
		mockMvc.perform(put("/api/v1/users/1/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(users)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.userPass", equalTo(USER_Pass)));
		then(usersServiceImpl).should().updateUser(anyLong(), any(UsersDto.class));
		then(usersServiceImpl).shouldHaveNoMoreInteractions();
	}
	
	@Test
	void test_UserUpdateStatusIs400() throws Exception
	{
		//given
		UsersDto users = new UsersDto();
		users.setUserPass(USER_Pass);
		users.setId(ID);
		
		//then
		mockMvc.perform(put("/api/v1/users/yyyyyyyyyyyyyyy/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(users)))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	void test_UserUpdateStatus400WhenProjectNotFound()
	{
		//given		
		given(usersServiceImpl.updateUser(anyLong(), any(UsersDto.class))).willThrow(UserNotFoundException.class);
		//when
		var response= usersController.updateUser(ID,new UsersDto());
		//Then
		assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
	}

	private UsersDto getUsersDto(String userMatricola) {
		UsersDto usersDto = new UsersDto();
		usersDto.setId(ID);
		usersDto.setUserName(USER_NAME);
		usersDto.setUserMatricola(userMatricola);
		usersDto.setUserPass(USER_Pass);
		usersDto.setUserRole(USER_Role);
		return usersDto;
	}
}
