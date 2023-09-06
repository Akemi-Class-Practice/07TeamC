package CTeam.com.ex.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import CTeam.com.ex.models.entity.AdminEntity;
import CTeam.com.ex.models.entity.LessonEntity;
import CTeam.com.ex.services.LessonService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LessonRegisterController {
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
		return"lecture_list.html";
		
	}
	
	//講座登録画面を取得
	@GetMapping("/lesson/register")
	public String getLessonRegisterPage(Model model) {
		AdminEntity adminEntity = (AdminEntity)session.getAttribute("admin");
		String adminName = adminEntity.getAdminName();
		model.addAttribute("adminName", adminName);
		return "lecture_registration.html";
	}
	
	
	//登録確認
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
	//データの処理
		@PostMapping("/lesson/register/process")
		
		public String lessonRegister(@RequestParam String lessonName,@RequestParam String lessonDetail,
				@RequestParam LocalDate startDate,@RequestParam LocalTime startTime,@RequestParam LocalTime finishTime,
				@RequestParam int lessonFee,
				@RequestParam String imageName,Model model) {
			AdminEntity adminEntity = (AdminEntity)session.getAttribute("admin");
			Long adminId = adminEntity.getAdminId();
			// 日時とファイルの名前を取得
			/*String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-ss-").format(new Date())
					+ imageName.getOriginalFilename();
			try {
				// 保存先を指定します
				Files.copy(imageName.getInputStream(), Path.of("src/main/resources/static/lesson-img/" + fileName));
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			// 登録した講座を保存します
			if(lessonService.createLessonPost(startDate, startTime, finishTime, lessonName, lessonDetail, lessonFee, imageName, startDate, adminId)) {
				   return "lecture_register_fix.html";
				}else {
					return "redirect:/lesson/register";
				}
		}
		
}
