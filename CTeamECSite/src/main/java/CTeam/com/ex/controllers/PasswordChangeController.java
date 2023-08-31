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

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;

@Controller
public class PasswordChangeController {

	@Autowired
	private HttpSession session;

	@Autowired
	private EmailVerificationService emailVerificationService;

	// 提供一个端点为前端生成CAPTCHA图片
	@GetMapping("/captcha")
	public ResponseEntity<byte[]> getCaptchaImage() throws Exception {
		BufferedImage captchaImage = emailVerificationService.generateCaptchaImage();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(captchaImage, "png", baos);
		byte[] imageBytes = baos.toByteArray();

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
	}

	@GetMapping("/password/reset")
	public String getPasswordResetPage() {

		return "password_reset.html";
	}

	@PostMapping("/password/reset/sencode")
	@ResponseBody
	public String sendVerificationCode(@RequestParam String email, @RequestParam String captchaValue, // 用户输入的CAPTCHA值
			Model model) {
		if (!emailVerificationService.verifyCaptcha(captchaValue)) {
			// CAPTCHA验证失败
			return "captcha_error";
		}

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

	@PostMapping("/password/reset/verify")
	public String verifyCode(@RequestParam String verificationCode, Model model) {
		String email = (String) session.getAttribute("email"); // 从会话中获取之前保存的邮箱
		if (email != null && emailVerificationService.verifyCode(email, verificationCode)) {
			// 验证成功，可以重定向到成功页面或其他操作
			return "redirect:/password/change";
		} else {
			// 验证失败，重定向到失败页面
			return "redirect:/error";
		}
	}

	@GetMapping("/password/change")
	public String getPasswordChangePage() {

		return "password_change.html";
	}

}
