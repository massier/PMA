package com.jrp.pma.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //Mandatory, testRepo MUST has same package name as main app (com.jrp.pma)
@RunWith(SpringRunner.class) //Mandatory

public class HTTPRequestTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemp;
	
	@Test
	public void homePageReturnsVersionNumberCorrectly_thenSuccess() {
		String renderedHtml = this.restTemp.getForObject("http://localhost:" + port + "/", String.class);//url, return type
		assertEquals(renderedHtml.contains("0.0.0"), true); //match app.properties
	}
	

}
