package CTeam.com.ex.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import CTeam.com.ex.services.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentRegistrationControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private StudentService studentService;
	
	@BeforeEach
	public void prepareData() {
		when(studentService.creatStudent(eq("Ana"), eq("Alice@a.com"), any())).thenReturn(false);
		when(studentService.creatStudent("Alice", "alice1234", "123456")).thenReturn(true);
	}
	@Test
	public void testGetRegisterPage_Succeed()throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/student/register");
			mockMvc.perform(request)
			.andExpect(view().name("user_info_registration.html"));
	}
	@Test
	public void testRegister_Succeed() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/student/register/confirm")
				.param("studentName","Alice")
				.param("studentEmail","alice1234")
				.param("studentPassword", "123456");
		
			mockMvc.perform(request)
			.andExpect(view().name("user_info_registration_confirmation.html")).andReturn();
	}
	
	@Test
	public void testGetStudentRegisterProcessPage() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/student/register/process")
				.param("studentName", "Alice")
				.param("studentEmail", "alice1234")
				.param("studentPassword", "123456");
		mockMvc.perform(request)
		.andExpect(view().name("user_login.html")).andReturn();
	}
}
