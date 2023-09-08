package CTeam.com.ex.controllers;

import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import CTeam.com.ex.models.entity.LessonEntity;
import CTeam.com.ex.models.entity.StudentEntity;
import CTeam.com.ex.services.LessonService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")

@Controller
public class UserLectureController {
	@Autowired
	private LessonService lessonService;

	@Autowired
	private HttpSession session;

	// 講座詳細画面を表示処理
	@GetMapping("/detail/{lessonId}")
	public String getLessonDetailPage(@PathVariable Long lessonId, Model model) {
		LessonEntity lesson = lessonService.findByLessonId(lessonId);
		model.addAttribute("lesson", lesson);
		model.addAttribute("loginFig", loginCheck());
		model.addAttribute("userName", loginUserName());
		return "lecture_details.html";
	}

	public boolean isLessonExist(Long lessonId, LinkedList<LessonEntity> list) {
		Iterator<LessonEntity> ite = list.iterator();
		boolean isExist = false;
		while (ite.hasNext()) {
			LessonEntity lesson = ite.next();
			if (lesson.getLessonId().equals(lessonId)) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}

	private String loginUserName() {
		if (loginCheck() == true) {
			StudentEntity student = (StudentEntity) session.getAttribute("student");
			String studentName = student.getStudentName();
			return studentName;
		} else {
			return null;
		}
	}

	private boolean loginCheck() {
		if (session.getAttribute("student") == null) {
			return false;
		} else {
			return true;
		}
	}

}
