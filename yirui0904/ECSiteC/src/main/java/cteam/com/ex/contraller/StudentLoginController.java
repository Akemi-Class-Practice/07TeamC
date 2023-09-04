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
	
	@GetMapping("/student/login")
	public String getStudentLoginPage() {
		return "user_login.html";
	}
	
	@GetMapping("/password")
	public String password() {
		return "password_change.html";
	}
	
	@PostMapping("/student/login/process")
	public String login(@RequestParam String studentEmail,
			@RequestParam String studentPassword,
			Model model) {
		StudentEntity student = studentService.loginCheck(studentEmail, studentPassword);
		if(student == null) {
			return "redirect:/student/login";
		}else {
			session.setAttribute("student", student);
			return "lecture_list.html";
		}
	}
	
}
