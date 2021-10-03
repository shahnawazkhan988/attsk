package com.example.attsk;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

import com.example.attsk.controller.UsersController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AttskApplicationTestIT {
	
	@Autowired
	UsersController usersController;
	@Test
	void contextLoads() {
		assertNotNull(usersController);
	}

}
