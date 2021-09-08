package com.example.attsk;

import org.junit.jupiter.api.*;
import org.mockito.*;

public class TestAttskApplication {

	@InjectMocks
	AttskApplication application;
	
	@Test
	void main()
	{
		String [] ary = {"start"};
		application.main(ary);
	}
}
