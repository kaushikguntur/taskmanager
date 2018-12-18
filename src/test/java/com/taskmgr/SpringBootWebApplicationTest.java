package com.taskmgr;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBootWebApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringBootWebApplicationTest {
	
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	@Test
	public void addTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/AddTask")
				 .contentType(MediaType.APPLICATION_JSON)
			      .content("{\"taskId\":1,\"taskName\":\"Parent task one\",\"startDate\":\"2018-10-01\",\"endDate\":\"2018-10-31\",\"priority\":10,\"parentId\":0,\"parentTask\":null,\"isTaskEnded\":0}")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isBoolean()).andDo(print());
	}
	
	@Test
	public void invalidDate() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/AddTask")
				 .contentType(MediaType.APPLICATION_JSON)
			      .content("{\"taskId\":1,\"taskName\":\"Parent task one\",\"startDate\":\"null\",\"endDate\":\"2018-10-31\",\"priority\":10,\"parentId\":0,\"parentTask\":null,\"isTaskEnded\":0}")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isBoolean()).andDo(print());
	}
	
	@Test
	public void getParent() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/AddTask")
				 .contentType(MediaType.APPLICATION_JSON)
			      .content("{\"taskId\":1,\"taskName\":\"Parent task one\",\"startDate\":\"2018-10-31\",\"endDate\":\"2018-10-31\",\"priority\":10,\"parentId\":1,\"parentTask\":\"parent\",\"isTaskEnded\":0}")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isBoolean()).andDo(print());
	}
	
	@Test
	public void getTaskList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/GetTaskList").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isArray()).andDo(print());
	}
	
	@Test
	public void ParentTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/ParentTask").accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isArray()).andDo(print());
	}
	
	@Test
	public void endTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/EndTask")
				 .contentType(MediaType.APPLICATION_JSON)
			      .content("{ \"taskId\": \"1\", \"taskName\" : \"Test\"}")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.isTaskEnded").value(1)).andDo(print());
	}
	
	@Test
	public void updateTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/UpdateTask")
				.contentType(MediaType.APPLICATION_JSON)
			      .content("{\"taskId\":2,\"taskName\":\"Chaild Task\",\"startDate\":\"2018-10-01\",\"endDate\":\"2018-10-31\",\"priority\":10,\"parentId\":0,\"parentTask\":null,\"isTaskEnded\":0}")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$").isBoolean()).andDo(print());
	}
	
	@Test
	public void getTask() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/GetTaskById/1")
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.taskId").exists()).andDo(print());
	}
}
