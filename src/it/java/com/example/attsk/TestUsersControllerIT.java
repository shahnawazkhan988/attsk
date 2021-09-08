package com.example.attsk;


import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.web.client.*;
import org.springframework.http.*;

import com.example.attsk.model.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestUsersControllerIT 
{
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	//private IUsersDao iUsersDao;
	
	void test_createNewUser() throws Exception
	{
		UsersDto user = new UsersDto();
		//user.setId(101L);
		user.setUserName("Shahnawaz");
		user.setUserMatricola("70009");
		user.setUserPass("123456");
		user.setUserRole("ST");
		
		HttpEntity<UsersDto> requestEntity = new HttpEntity<>(user);
		ResponseEntity<UsersDto> responseEntity = testRestTemplate.postForEntity("/api/v1/users/", requestEntity, UsersDto.class);
//		
		
		assertNotNull(responseEntity.getBody().getId());
		assertEquals("Shahnawaz", requestEntity.getBody().getUserName());
		assertEquals("70009", requestEntity.getBody().getUserMatricola());
		assertEquals("123456", requestEntity.getBody().getUserPass());
		assertEquals("ST", requestEntity.getBody().getUserRole());
		
		
//		Response response = given().
//				contentType(MediaType.APPLICATION_JSON_VALUE).
//				body(user).
//			when().
//				post("/api/v1/users/");
//
//		UsersDto saved = response.getBody().as(UsersDto.class);
//
//			// read it back from the repository
//			assertThat(iUsersDao.findById(saved.getId()))
//				.contains(saved);
	}
}
