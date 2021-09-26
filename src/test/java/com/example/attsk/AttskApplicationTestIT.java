package com.example.attsk;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AttskApplicationTestIT {

	@Test
	void contextLoads() {
	}

	// Test class added ONLY to cover main() invocation not covered by application tests.
//	@Test	
//	void main() {
//		AttskApplication.main(new String[] {});
//	   }
}
