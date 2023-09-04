package cteam.com.ex.contraller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cteam.com.ex.model.entity.AdminEntity;
import cteam.com.ex.service.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private HttpSession httpSession;
	
	@PostMapping("/admin/login/process")
	public String getAdminLoginProcessPage(@RequestParam String adminName,
			@RequestParam String adminPassword,
			Model model) {
		AdminEntity adminEntity = adminService.findByAdminNameAndAdminPassword(adminName, adminPassword);
		if(adminEntity == null) {
			return "redirect:/admin/login";
		}else {
//			httpSession.setAttribute("adminEmail", adminEntity);
			return "lecture_list.html";
		}
	}
}
