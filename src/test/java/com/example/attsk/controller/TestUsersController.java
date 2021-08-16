package com.example.attsk.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

import com.example.attsk.model.*;
import com.example.attsk.service.*;
import com.fasterxml.jackson.databind.*;

@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
 class TestUsersController 
{
	public static final Long ID = 1L;
    public static final String USER_NAME = "userName";
    public static final String USER_Matricola = "70001";
    public static final String USER_Pass = "userPAss";
    public static final String USER_Role = "ST";
    
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
	 void test_getAllUsers()
	{
		
		//given
		
		List<UsersDto> users = new ArrayList<UsersDto>();
		UsersDto user = new UsersDto();
		UsersDto user1 = new UsersDto();
		user.setId(1L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");
		
		user1.setId(1L);
		user1.setUserName("User2");
		user1.setUserMatricola("70002");
		user1.setUserPass("123456");
		user1.setUserRole("ST");
		
		users.add(user);
		users.add(user1);
		
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
	
	
	@Test
	 void test_createNewUser() throws NoSuchMethodException
	{
		//given
        UsersDto usersDto = getUsersDto(USER_Matricola);        
        given(usersServiceImpl.createNewUser(any(UsersDto.class))).willReturn(usersDto);
		
        //when
		 ResponseEntity<Object> user1 = usersController.createNewUser(usersDto,bindingResult);
	     System.out.println(user1);
				
		//then
		assertNotNull(user1.getBody());
				
		//assert
		assertEquals(HttpStatus.CREATED,user1.getStatusCode());
        assertEquals(usersDto.getClass().getDeclaredMethod("getUserMatricola"), Objects.requireNonNull(user1.getBody()).getClass().getDeclaredMethod("getUserMatricola"));
        then(usersServiceImpl).should().createNewUser(any(UsersDto.class));
        then(usersServiceImpl).shouldHaveNoMoreInteractions();
		
	}
	
	
	@Test
    void test_createNewUserStatusIsOK() throws Exception {
        
		//given
		UsersDto usersDto = getUsersDto(USER_Matricola);        
        given(usersServiceImpl.createNewUser(any(UsersDto.class))).willReturn(usersDto);

        //When
        mockMvc.perform(post("/api/v1/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(usersDto)))
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.userName",equalTo(USER_NAME)))
//                .andExpect(jsonPath("$.userMatricola",equalTo(USER_Matricola)))
//                .andExpect(jsonPath("$.userPass",equalTo(USER_Pass)))
//        		.andExpect(jsonPath("$.userRole",equalTo(USER_Role)));
    }
	
	@Test
	  void test_createNewUserStatusIs400() throws Exception {
		//given
		  UsersDto usersDto = new UsersDto();
	        usersDto.setId(ID);
	        usersDto.setUserName(USER_NAME);
	        usersDto.setUserMatricola("Iden8kkkkkkkkkkk888");
	        usersDto.setUserPass(USER_Pass);
	        usersDto.setUserRole(USER_Role);


	        //When
	        mockMvc.perform(post("/api/v1/users")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(mapper.writeValueAsString(usersDto)))
	                .andExpect(status().isBadRequest())
	                .andExpect(jsonPath("$.userMatricola", Is.is("Please use 4 to 5 characters long")));

	    }

	@Test
    void test_createNewUserStatusIs400withEmptyProject() throws Exception {
		//given
		UsersDto usersDto = new UsersDto();
		usersDto.setId(ID);


		//When
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(usersDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.userName", Is.is("User Name is required")))
                .andExpect(jsonPath("$.userMatricola",Is.is("userMatricola is required")))
                .andExpect(jsonPath("$.userPass",Is.is("User userPass is required")));

    }

	
	@Test
	 void test_deleteUser()
	{
		//given
		UsersDto users = new UsersDto();
		users.setId(1L);
		users.setUserName("Shahnawaz");
		users.setUserMatricola("70001");
		users.setUserPass("123456");
		users.setUserRole("ST");
		
		//delete
		usersController.deleteUser(users);
		
		//verify
		verify(usersServiceImpl, times(1)).deleteUser(users);
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
