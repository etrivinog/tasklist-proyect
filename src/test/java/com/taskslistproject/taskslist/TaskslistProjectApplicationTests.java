package com.taskslistproject.taskslist;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@Rollback(true)
class TaskslistProjectApplicationTests {
	
	@Test
	void contextLoads() {
	}
	
}
