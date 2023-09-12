package cteam.com.ex.contraller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cteam.com.ex.model.entity.AdminEntity;
import cteam.com.ex.model.entity.LessonEntity;
import cteam.com.ex.service.LessonService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLectureController {
	@Autowired
	private LessonService lessonService;
	@Autowired
	private HttpSession session;
	
	//講座一覧画面を取得します
	@GetMapping("/lesson/list")
	public String getLessonListPage(Model model) {
		// admin情報を取得
		AdminEntity adminEntity = (AdminEntity)session.getAttribute("admin");
		Long adminId = adminEntity.getAdminId();
		String adminName = adminEntity.getAdminName();
		// このアカンウトに関連する講座投稿を取得します
		List<LessonEntity>lessonList = lessonService.findAllLessonPost(adminId);
		model.addAttribute("adminName", adminName);
		model.addAttribute("lessonList", lessonList);
		return "lecture_list.html";
	}
	
	//講座登録画面を取得
	@GetMapping("/lesson/register")
	public String getLessonRegisterPage(Model model) {
		AdminEntity adminEntity = (AdminEntity)session.getAttribute("admin");
		String adminName = adminEntity.getAdminName();
		model.addAttribute("adminName", adminName);
		return "lecture_registration.html";
	}
		
	//登録情報確認
	@PostMapping("/lesson/register/process/confirm")
	public String confirmLessonRegistration(@RequestParam String lessonName,@RequestParam String lessonDetail,
			@RequestParam LocalDate startDate,@RequestParam LocalTime startTime,@RequestParam LocalTime finishTime,
			@RequestParam int lessonFee,
			@RequestParam MultipartFile imageName,Model model) {
		
		// 日時とファイルの名前を取得
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-ss-").format(new Date())
				+ imageName.getOriginalFilename();
		try {
			// 保存先を指定します
			Files.copy(imageName.getInputStream(), Path.of("src/main/resources/static/lesson-img/" + fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		//登録した内容を画面に表示
		model.addAttribute("lessonName",lessonName);
		model.addAttribute("lessonDetail",lessonDetail);
		model.addAttribute("startDate",startDate);
		model.addAttribute("startTime",startTime);
		model.addAttribute("finishTime",finishTime);
		model.addAttribute("lessonFee",lessonFee);
		model.addAttribute("imageName",fileName);
			
		return "lecture_registration_confirmation.html";
	}
	
	//登録処理
	@PostMapping("/lesson/register/process")
	public String lessonRegister(@RequestParam String lessonName,@RequestParam String lessonDetail,
			@RequestParam LocalDate startDate,@RequestParam LocalTime startTime,@RequestParam LocalTime finishTime,
			@RequestParam int lessonFee,
			@RequestParam String imageName,Model model) {
		AdminEntity adminEntity = (AdminEntity)session.getAttribute("admin");
		Long adminId = adminEntity.getAdminId();
		// 登録した講座を保存します
		if(lessonService.createLessonPost(startDate, startTime, finishTime, lessonName, lessonDetail, lessonFee, imageName, startDate, adminId)) {
			return "lecture_register_fix.html";
		}else {
			return "redirect:/lesson/register";
		}
	}
	
	//講座情報詳細
	@GetMapping("/lesson/details")
	public String getLessonDetails() {
		return "lecture_details.html";
	}
	
	//講座更新
	@GetMapping("/lesson/edit/{lessonId}")
	public String getLessonEditPage(@PathVariable Long lessonId,Model model) {
		//ログインしてるadmin情報を取得する
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		//ログインしている人の名前と講座listを取得
		String loginAdminName = admin.getAdminName();
		if(admin == null) {
			return "redirect:/admin/login";
		}else {
			LessonEntity lessonList = lessonService.findByLessonId(lessonId);
			if(lessonList != null) {
				model.addAttribute("loginAdminName",loginAdminName);
				model.addAttribute("lesson",lessonList);
				return "lecture_edit.html";
			}else {
				return "";
			}
		}
	}
	//講座更新処理
	@PostMapping("/edit/update")
	public String editLesson(@RequestParam Long lessonId,
			@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
			@RequestParam(name = "finishTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime finishTime,
			@RequestParam String lessonName,
			@RequestParam String lessonDetail,
			@RequestParam int lessonFee,
			Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		Long adminId = admin.getAdminId();
		lessonService.editLesson(lessonId, startDate, startTime, finishTime, lessonName, lessonDetail, lessonFee,adminId);
		model.addAttribute("lessonId",lessonId);
		return "lecture_edit_completed";
	}
	
	//講座画像更新画面取得
	@GetMapping("/imag/edit/{lessonId}")
	public String GetImageEditPage(@PathVariable Long lessonId,Model model) {
		//ログインしてるadmin情報を取得する
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		String loginAdminName = admin.getAdminName();
		if(admin == null) {
			return "redirect:/admin/login";
		}else {
			LessonEntity lessonList = lessonService.findByLessonId(lessonId);
			if(lessonList != null) {
				model.addAttribute("loginAdminName",loginAdminName);
				model.addAttribute("lesson",lessonList);
				return "lecture_image_edit.html";
			}else {
				return "";
			}
		}
	}
	
	//講座画像更新処理
	@PostMapping("/image/edit/update")
	public String editImage(@RequestParam Long lessonId,
			@RequestParam("imageName") MultipartFile imageName,
			Model model) {
		AdminEntity admin = (AdminEntity) session.getAttribute("admin");
		Long adminId = admin.getAdminId();
		if(admin != null) {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + imageName.getOriginalFilename();
			try {
				Files.copy(imageName.getInputStream(), Path.of("src/main/resources/static/lesson-img/" + fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(lessonService.editImageLesson(lessonId, fileName, adminId)) {
				model.addAttribute("lessonId",lessonId);
				return "lecture_edit_completed";
			}
		}
		return null;
		
	}	
	
	//削除処理画面取得
	@GetMapping("/delete/detail/{lessonId}")
	public String getLessonDeleteDetailPage(@PathVariable Long lessonId,Model model) {
		// admin情報を取得
		/*AdminEntity adminEntity = (AdminEntity)session.getAttribute("admin");
		String adminName = adminEntity.getAdminName();
		model.addAttribute("adminName", adminName);*/
		LessonEntity lessonList = lessonService.findByLessonId(lessonId);
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
