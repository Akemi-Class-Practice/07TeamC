package CTeam.com.ex.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CTeam.com.ex.models.entity.AdminEntity;
import CTeam.com.ex.models.entity.LessonEntity;
import CTeam.com.ex.services.LessonService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LessonDeleteController {
	
	@Autowired
	private LessonService lessonService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/delete/detail/{lessonId}")
	public String getLessonDeleteDetailPage(@PathVariable Long lessonId,Model model) {
		// admin情報を取得
		/*AdminEntity adminEntity = (AdminEntity)session.getAttribute("admin");
		String adminName = adminEntity.getAdminName();
		model.addAttribute("adminName", adminName);*/
		LessonEntity lessonList = lessonService.getLessonPost(lessonId);
		if(lessonList == null) {
			return"redirecr:/lesson/list";
		}else {
			model.addAttribute("lessonList", lessonList);
			return "lecture_delete.html";
		}
	}
	
	@PostMapping("/lesson/delete")
	public String LessonDelete(@RequestParam Long lessonId) {
		if(lessonService.deleteLesson(lessonId)) {
			return"lecture_delete_fix.html";
		}else {
			return "lecture_delete.html";
		}
	}
}
