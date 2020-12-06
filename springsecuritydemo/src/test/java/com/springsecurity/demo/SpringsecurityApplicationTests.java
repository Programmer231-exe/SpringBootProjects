package com.springsecurity.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringsecurityApplicationTests {

	@Test
	public void givenUserWithReadScope_whenGetResources_thenSuccess(){
		String accessToken = obtainAccessToken()
	}

}
