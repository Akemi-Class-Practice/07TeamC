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
	
	@GetMapping("/student/login")
	public String getStudentLoginPage() {
		return "user_login.html";
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
			return "lecture_list";
		}
	}
}
