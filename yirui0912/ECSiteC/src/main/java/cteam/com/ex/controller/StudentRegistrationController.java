package cteam.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cteam.com.ex.service.StudentService;

@Controller
public class StudentRegistrationController {

	@Autowired
	private StudentService studentService;
	
	//登録画面表示
	@GetMapping("/student/register")
	private String getStudentRegisterPage() {
		return "user_info_registration.html";
	}
	
	//登録後の情報内容確認
	@PostMapping("/student/register/confirm")
	public String getStudentRegisterConfirmPage(@RequestParam String studentName,
			@RequestParam String studentEmail,
			@RequestParam String studentPassword,
			Model model) {
		model.addAttribute("studentName",studentName);
		model.addAttribute("studentEmail",studentEmail);
		model.addAttribute("studentPassword",studentPassword);
		return "user_info_registration_confirmation.html";
	}
	
	//登録機能
	@PostMapping("/student/register/process")
	public String getStudentRegisterProcessPage(@RequestParam String studentName,
			@RequestParam String studentEmail,
			@RequestParam String studentPassword) {
		studentService.creatStudent(studentName, studentEmail, studentPassword);
		return "user_login.html";
	}
	
}
