package CTeam.com.ex.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import CTeam.com.ex.services.AdminService;
import jakarta.servlet.http.HttpSession;

@SpringBootTest
@AutoConfigureMockMvc

public class AdminRegisterControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	private MockHttpSession session;
	
	@BeforeEach
	public void prepareDate() {
		session = new MockHttpSession();
		    String adminName = "Alice";
		    String adminEmail = "alice1234@a.com";
		    String adminPassword = "123456";
		    
		session.setAttribute("adminName", adminName);
		session.setAttribute("adminEmail", adminEmail);
		session.setAttribute("adminPassword", adminPassword);
		when(adminService.createAdmin("Jim", "Jim@a.com","123456")).thenReturn(false);
		when(adminService.createAdmin("Alice", "alice1234@a.com","123456")).thenReturn(true);
		when(adminService.beforeCreateAdmin_Confirm("alice1234@a.com")).thenReturn(true);
		when(adminService.createAdmin("Alice", "alice1234@a.com", "")).thenReturn(false);
	}
	
	@Test
	public void testShowRegisterPage() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/admin/register");
		mockMvc.perform(request)
		.andExpect(view().name("admin_registration.html"));
	}
	
	//成功
	@Test
	public void testRegister_Succeed()  throws Exception{
	    String adminName = "Alice";
	    String adminEmail = "alice1234@a.com";
	    String adminPassword = "123456";
	
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/register/process")
				.param("adminName", adminName)
				.param("adminEmail", adminEmail)
				.param("adminPassword", adminPassword);
		mockMvc.perform(request)
		.andExpect(view().name("admin_register_confirm.html"));
		
	}
	
	@Test
	public void testRegister_Fail() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/register/process")
				.param("adminName","Alice")
				.param("adminEmail","123@123.com")
				.param("adminPassword", "123");
		
			mockMvc.perform(request)
			.andExpect(view().name("redirect:/admin/register"));
	}

	@Test
	public void testGetadminLoginPage() throws Exception{
		RequestBuilder request = MockMvcRequestBuilders
				.get("/admin/login");
		mockMvc.perform(request)
		.andExpect(view().name("admin_login.html"));
	}
	
	
	@Test
	public void testConfirmAdminRegistration_Sucess() throws Exception{
		/*
		 * String adminName = "Alice"; String adminEmail = "Alice1234@a.com"; String
		 * adminPassword = "123456";
		 */
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/register/process/confirm")
				.session(session);
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/admin/login"));
	}
	
	@Test
	public void testConfirmAdminRegistration_Fail() throws Exception{
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("adminName", "Alice");
		session.setAttribute("adminEmail", "alice1234@a.com");
		session.setAttribute("adminPassword", "");
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/register/process/confirm")
				.session(session);
		mockMvc.perform(request)
		.andExpect(redirectedUrl("/admin/register")).andReturn();
	}

}
