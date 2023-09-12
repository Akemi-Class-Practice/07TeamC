package cteam.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cteam.com.ex.model.entity.StudentEntity;
import cteam.com.ex.service.StudentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentLoginController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private HttpSession session;
	
	//ユーザーログイン機能
	@GetMapping("/student/login")
	public String getStudentLoginPage() {
		return "user_login.html";
	}
	@PostMapping("/student/login/process")
	public String login(@RequestParam String studentEmail,
			@RequestParam String studentPassword,
			Model model) {
		StudentEntity studentEntity = studentService.loginCheck(studentEmail, studentPassword);
		if(studentEntity == null) {
			return "redirect:/student/login";
		}else {
			//studentEntityの内容をsessionに保存する
			session.setAttribute("student", studentEntity);
			
			//ユーザー情報を取得する
			StudentEntity student = (StudentEntity) session.getAttribute("student");
			String LoginName = student.getStudentName();
			model.addAttribute("loginName",LoginName);
			String url = (String) session.getAttribute("goLogin");
			if(url == null) {
				return "lecture_list.html";
			}else {
				return "redirect"+url;
			}
		}
	}
	
	//パスワード変更画面
	@GetMapping("/student/password/change")
	public String getPasswordChangePage(Model model) {
		//AdminEntity password = adminService.findByAdminId(adminId);
		//model.addAttribute("newPassword",password);
		return "password_change.html";
	}
	//パスワード変更処理
	@PostMapping("/student/password/change/complete")
	public String getPasswordChangeComplete(@RequestParam String password, 
			@RequestParam String passwordConfirm, 
			Model model) {
		String email = (String) session.getAttribute("email");
		if(password.equals(passwordConfirm)) {
			studentService.updatePassword(email, password);
			return "password_change_completed.html";
		}else {
			return "";
		}
	}
	
//	//パスワード変更画面
//	@GetMapping("/password/change")
//	public String getSPasswordChangePage(@PathVariable Long studentId,Model model) {
//		StudentEntity password = studentService.findByStudentId(studentId);
//		model.addAttribute("studentEmail",password);
//		return "password_change.html";
//	}
//	//パスワード変更処理
//	@PostMapping("/password/change/complete")
//	public String getSPasswordChangeComplete(@RequestParam String studentEmail, 
//			@RequestParam String password, Model model) {
//		studentService.updatePassword(studentEmail, password);
//		return "password_change_complete.html";
//	}
	
//	//logout
//	@GetMapping("/logout")
//	public String userLogout(){
//		session.invalidate();
//		return "redirect:/student/login";
//	}
	
}
