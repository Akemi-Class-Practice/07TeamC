package cteam.com.ex.contraller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	//パスワード変更機能
	@GetMapping("/password")
	public String password() {
		return "password_change.html";
	}
	@PostMapping("/password/email")
	public String passwordEmailSent(@RequestParam String userEmail,Model model) {
		return "";
	}
	
//	//logout
//	@GetMapping("/logout")
//	public String userLogout(){
//		session.invalidate();
//		return "redirect:/student/login";
//	}
	
	
}
