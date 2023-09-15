package CTeam.com.ex.controllers;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import CTeam.com.ex.models.entity.AdminEntity;
import CTeam.com.ex.services.AdminService;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminLoginControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	@BeforeEach
	public void prepareDate() {
		AdminEntity adminEntity = new AdminEntity("Akemi","a@b.com","123456",1,LocalDate.now());
		when(adminService.findByAdminNameAndAdminPassword(eq("Akemi"),eq("123456"))).thenReturn(adminEntity);
		when(adminService.findByAdminNameAndAdminPassword(eq("Akemi"),eq("abcd"))).thenReturn(null);
		
	}
	
	@Test
	public void testGetAdminLoginProcessPage_Succed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/login/process")
				.param("adminName", "Akemi")
				.param("adminPassword", "123456");
		
		mockMvc.perform(request)
			.andExpect(redirectedUrl("/lesson/list")).andReturn();
	}
	
	@Test
	public void testGetAdminLoginpassword_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/login/process")
				.param("adminName", "Akemi")
				.param("adminPassword", "abcd");
		
		mockMvc.perform(request)
			.andExpect(redirectedUrl("/admin/login")).andReturn();
	}
	
	@Test
	public void testGetAdminLoginadminName_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/login/process")
				.param("adminName", "Ddcc")
				.param("adminPassword", "123456");
		
		mockMvc.perform(request)
			.andExpect(redirectedUrl("/admin/login")).andReturn();
	}
	
	//password null
	@Test
	public void testGetAdminLoginpasswordnull_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/admin/login/process")
				.param("adminName", "Akemi")
				.param("adminPassword", "");
		
		mockMvc.perform(request)
			.andExpect(redirectedUrl("/admin/login")).andReturn();
	}
	
	//adminName null
		@Test
		public void testGetAdminLoginadminNamenull_Fail() throws Exception {
			RequestBuilder request = MockMvcRequestBuilders
					.post("/admin/login/process")
					.param("adminName", "")
					.param("adminPassword", "123456");
			
			mockMvc.perform(request)
				.andExpect(redirectedUrl("/admin/login")).andReturn();
		}
}
