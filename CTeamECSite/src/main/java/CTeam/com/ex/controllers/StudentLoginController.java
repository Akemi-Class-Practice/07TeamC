package CTeam.com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CTeam.com.ex.models.entity.StudentEntity;
import CTeam.com.ex.services.StudentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentLoginController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private HttpSession session;
	
	//ログイン画面の取得
	@GetMapping("/student/login")
	public String getStudentLoginPage() {
		return "user_login.html";
	}
	//ログイン処理
	@PostMapping("/student/login/process")
	public String login(@RequestParam String studentEmail,
			@RequestParam String studentPassword,
			Model model) {
		StudentEntity studentEntity = studentService.loginCheck(studentEmail, studentPassword);
		if(studentEntity == null) {
			return "redirect:/student/login";
		}else {
			session.setAttribute("student", studentEntity);
			//ユーザー情報を取得する
			StudentEntity student = (StudentEntity) session.getAttribute("student");
			String LoginName = student.getStudentName();
			model.addAttribute("loginName",LoginName);
			String url = (String) session.getAttribute("goLogin");
			if(url == null) {
				return "redirect:/user/menu";
			}else {
				return "redirect"+url;
			}
		}
	}
	
	//パスワード変更画面の取得
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
				return "password_change.html";		
			
		}
	}
 	//logout
       @GetMapping("/student/logout")
	public String userLogout(){
		session.invalidate();
		return "redirect:/student/login";
	}
}
