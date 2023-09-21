package CTeam.com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import CTeam.com.ex.models.entity.EmailVerificationEntity;
import CTeam.com.ex.services.EmailVerificationService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PasswordChangeController {

	@Autowired
	private HttpSession session;

	@Autowired
	private EmailVerificationService emailVerificationService;

	// パスワード変更画面の取得
	@GetMapping("/password/reset")
	public String getPasswordResetPage() {

		return "password_reset.html";
	}

	// 検証コードのメール送信処理
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

	// コードの検証処理
	@PostMapping("/password/reset/verify")
	public String verifyCode(@RequestParam String verificationCode, Model model) {
		String email = (String) session.getAttribute("email"); // 从会话中获取之前保存的邮箱
		if (email != null && emailVerificationService.verifyCode(email, verificationCode)) {
			// 成功した場合パスワード更新の画面に飛ぶ
			return "redirect:/password/change";
		} else {
			// 失敗した場合
			return "redirect:/error";
		}
	}

	// パスワードリセット画面の取得
	@GetMapping("/password/change")
	public String getPasswordChangePage() {

		return "password_change.html";
	}

}
