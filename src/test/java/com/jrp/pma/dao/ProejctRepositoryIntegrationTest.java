package com.jrp.pma.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jrp.pma.entities.Project;
//RUN: right click -> run as -> 1 JUnit Test

@SpringBootTest //Mandatory, testRepo MUST has same package name as main app (com.jrp.pma)
@RunWith(SpringRunner.class) //Mandatory

//Optional: Populate before test & clean up after
//classpath ato scans all classes
//@SqlGroup({
//    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
//    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")
//})
public class ProejctRepositoryIntegrationTest {
	@Autowired
	IProjectRepository proRep;
	
	@Test
	public void ifNewProjectSaved_thenSuccess() {
		//Testing: no need to define schema b/c hibernate.ddl-auto is ON
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
		proRep.save(newProject);
		assertEquals(1, proRep.findAll().size());
	}
	

}
