package cteam.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cteam.com.ex.model.entity.AdminEntity;
import cteam.com.ex.model.entity.StudentEntity;
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
			httpSession.setAttribute("admin", adminEntity);
			return "redirect:/lesson/list";
		}
	}
	
	@GetMapping("/logout")
	public String adminLogout() {
		httpSession.invalidate();
		return "redirect:/admin/login";
	}

	/*
	 * この機能は使いません！！
	 * 実行できます！！
	 */
//	//パスワード変更画面
//	@GetMapping("/admin/password/change")
//	public String getPasswordChangePage(Model model) {
//		//AdminEntity password = adminService.findByAdminId(adminId);
//		//model.addAttribute("newPassword",password);
//		return "password_change.html";
//	}
//	
//	//パスワード変更処理
//	@PostMapping("/admin/password/change/complete")
//	public String getPasswordChangeComplete(@RequestParam String password, 
//			@RequestParam String passwordConfirm, 
//			Model model) {
//		String email = (String) httpSession.getAttribute("email");
//		if(password.equals(passwordConfirm)) {
//			adminService.updatePassword(email, password);
//			return "password_change_completed.html";
//		}else {
//			return "";
//		}
//	}
}
