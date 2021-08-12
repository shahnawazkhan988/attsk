package com.example.attsk.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.util.*;

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
public class TestUsersController 
{
	public static final long ID = 1;
    public static final String USER_NAME = "userName";
    public static final String USER_Matricola = "userMatricola";
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
	public void test_getAllUsers()
	{
		
		//given
		
		List<UsersDto> users = new ArrayList<UsersDto>();
		UsersDto user = new UsersDto();
		UsersDto user1 = new UsersDto();
		user.setId(1);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70001");
		user.setUserPass("123456");
		user.setUserRole("ST");
		
		user1.setId(1);
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
	public void test_createNewUser() throws NoSuchMethodException
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
		usersController.deleteUser(users);
		
		//verify
		verify(usersServiceImpl, times(1)).deleteUser(users);
	}
	
	
	private UsersDto getUsersDto(String userMatricola) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(0);
        usersDto.setUserName(USER_NAME);
        usersDto.setUserMatricola(userMatricola);
        usersDto.setUserPass(USER_Pass);
        usersDto.setUserRole(USER_Role);
        return usersDto;
    }
}
