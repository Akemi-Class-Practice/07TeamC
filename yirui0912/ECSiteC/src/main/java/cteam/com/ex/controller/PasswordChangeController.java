package cteam.com.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cteam.com.ex.model.entity.EmailVerificationEntity;
import cteam.com.ex.service.EmailVerificationService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PasswordChangeController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EmailVerificationService emailVerificationService;
	
	//パスワードリセットページ　OK!
	@GetMapping("/password/reset")
	public String getPasswordResetPage() {
		return "password_reset.html";
	}
	
	//メールを発送メソッド　OK!
	@PostMapping("/password/reset/sencode")
	@ResponseBody
	public String sendVerificationCode(@RequestParam String email, Model model) {
	    try {
	        EmailVerificationEntity verificationEntity = emailVerificationService.generateVerificationCode(email);
	        emailVerificationService.sendVerificationCodeEmail(email, verificationEntity.getVerificationCode());
	        session.setAttribute("email", email); // 保存邮箱到会话
	        return "success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}
	
	//認証
	@PostMapping("/password/reset/verify")
	public String verifyCode(@RequestParam String verificationCode, Model model) {
	    String email = (String) session.getAttribute("email"); // 从会话中获取之前保存的邮箱
	    if (email != null && emailVerificationService.verifyCode(email, verificationCode)) {
	        // 验证成功，可以重定向到成功页面或其他操作
	    	//コードが合ってる場合、次の画面へ
	        return "redirect:/student/password/change"; 
	    } else {
	        // 验证失败，重定向到失败页面
	    	//コードがあってない場合、エラー！
	        return "redirect:/error"; 
	    }
	}
	
}
