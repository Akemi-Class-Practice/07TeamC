package CTeam.com.ex.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import CTeam.com.ex.models.entity.StudentEntity;
import CTeam.com.ex.services.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentLoginControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private StudentService studentService;

	@BeforeEach
	public void prepareDate() {
		StudentEntity studentEntity = new StudentEntity("Akemi", "a@b.com", "123456", 1, LocalDate.now());
		when(studentService.creatStudent("Akemi", "a@b.com", "123456")).thenReturn(true);
		when(studentService.creatStudent("Akemi", "a@b.com", "abcd")).thenReturn(false);
		when(studentService.loginCheck("a@b.com", "123456")).thenReturn(studentEntity);
		when(studentService.loginCheck("a@b.com", "abcd")).thenReturn(null);
	}

	@Test
	public void testGetStudentLoginPage_succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/student/login");
		mockMvc.perform(request).andExpect(view().name("user_login.html"));
	}

	@Test
	public void testGetStudentLoginCheck_succeed_UrlIsNotNull() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("goLogin", "/user/mypage");
		RequestBuilder request = MockMvcRequestBuilders.post("/student/login/process").param("studentEmail", "a@b.com")
				.param("studentPassword", "123456").session(session);
		mockMvc.perform(request).andExpect(redirectedUrl("/user/mypage")).andReturn();
	}

	@Test
	public void testGetStudentLoginCheck_succeed_UrlIsNull() throws Exception {
		MockHttpSession session = new MockHttpSession();
		RequestBuilder request = MockMvcRequestBuilders.post("/student/login/process").param("studentEmail", "a@b.com")
				.param("studentPassword", "123456");
		mockMvc.perform(request).andExpect(redirectedUrl("/user/menu")).andReturn();
	}

	// email null
	@Test
	public void testGetStudentemailnull_fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/student/login/process").param("studentEmail", "")
				.param("studentPassword", "123456");
		mockMvc.perform(request).andExpect(redirectedUrl("/student/login")).andReturn();
	}

	// email
	@Test
	public void testGetStudentemail_fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/student/login/process").param("studentEmail", "abcd")
				.param("studentPassword", "123456");
		mockMvc.perform(request).andExpect(redirectedUrl("/student/login")).andReturn();
	}

	// password null
	@Test
	public void testGetStudentpasswordnull_fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/student/login/process").param("studentEmail", "a@b.com")
				.param("studentPassword", "");
		mockMvc.perform(request).andExpect(redirectedUrl("/student/login")).andReturn();
	}

	// password
	@Test
	public void testGetStudentpassword_fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/student/login/process").param("studentEmail", "a@b.com")
				.param("studentPassword", "abcd");
		mockMvc.perform(request).andExpect(redirectedUrl("/student/login")).andReturn();
	}
	
	@Test
	public void testGetPasswordChangePage() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/student/password/change");
		mockMvc.perform(request).andExpect(view().name("password_change.html")).andReturn();
	}
	
	@Test
	public void testUserLogout() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders.get("/student/logout");
		mockMvc.perform(request)
			.andExpect(redirectedUrl("/student/login"))
			.andReturn();
	}
	
	@Test
	public void testGetPasswordChangeComplete_success() throws Exception{
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("email", "a@b.com");
		RequestBuilder request = MockMvcRequestBuilders.post("/student/password/change/complete")
				.param("password", "123456")
				.param("passwordConfirm", "123456")
				.session(session);
		mockMvc.perform(request)
			.andExpect(view().name("password_change_completed.html"));
				
	}
	
	@Test
	public void testGetPasswordChangeComplete_fail() throws Exception{
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("email", "a@b.com");
		RequestBuilder request = MockMvcRequestBuilders.post("/student/password/change/complete")
				.param("password", "123456")
				.param("passwordConfirm", "654321")
				.session(session);
		mockMvc.perform(request)
			.andExpect(view().name("password_change.html"));
				
	}

}
