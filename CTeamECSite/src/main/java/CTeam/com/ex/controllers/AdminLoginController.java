package CTeam.com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CTeam.com.ex.models.entity.AdminEntity;
import CTeam.com.ex.services.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private HttpSession session;

	// 送信されたログインデータの処理
	@PostMapping("/admin/login/process")
	public String getAdminLoginProcessPage(@RequestParam String adminName, @RequestParam String adminPassword,
			Model model) {
		// adminEntityのなか対応する名前とパスワードを検索します
		AdminEntity adminEntity = adminService.findByAdminNameAndAdminPassword(adminName, adminPassword);
		// なかった場合このページのまま
		if (adminEntity == null) {
			return "redirect:/admin/login";
			// あった場合一覧ページに飛ぶ
		} else {
			session.setAttribute("admin", adminEntity);
			return "redirect:/lesson/list";
		}
	}
}
