package CTeam.com.ex.controllers;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CTeam.com.ex.models.entity.LessonEntity;
import CTeam.com.ex.models.entity.StudentEntity;
import CTeam.com.ex.models.entity.SubscriptionEntity;
import CTeam.com.ex.services.LessonService;
import CTeam.com.ex.services.SubscriptionService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")

@Controller
public class UserLectureController {
	@Autowired
	LessonService lessonService;

	@Autowired
	HttpSession session;

	@Autowired
	SubscriptionService subscriptionService;

	// 講座詳細画面を表示処理
	@GetMapping("/lesson/detail/{lessonId}")
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

	// ログインページに飛ばすメソッド
	public String login(String templete) {
		session.setAttribute("goLogin", templete);
		return "redirect:/user/login";
	}

	// mypageの画面取得
	@GetMapping("/mypage")
	public String getMypage(Model model) {
		if (session.getAttribute("student") == null) {
			return login("/user/mypage");
		} else {
			StudentEntity student = (StudentEntity) session.getAttribute("student");
			Long studentId = student.getStudentId();
			List<SubscriptionEntity> listSub = subscriptionService.getPurchaseHistory(studentId);
			model.addAttribute("listSub", listSub);
			model.addAttribute("loginFig", loginCheck());
			model.addAttribute("userName", student.getStudentName());
			return "mypage.html";
		}
	}

	
	// カート一覧
	@PostMapping("/cart/list")
	public String getCartListPage(@RequestParam Long lessonId, Model model) {
		// StudentEntity student = null;
		// ログインしている人の情報を取得する
		StudentEntity student = (StudentEntity) session.getAttribute("student");
		// もしログインしている人の情報が存在しなかったらログイン画面に遷移する
		if (student == null) {
			return "redirect:/student/login";
			// もしカートに何も情報がなかった場合（カートに存在する商品が０個の場合）
		} else if (session.getAttribute("cart") == null) {
			// カート情報を格納しておくための変数を作成
			LinkedList<LessonEntity> list = new LinkedList<LessonEntity>();
			// lessonIDを使用して購入する製品の情報を取得する
			LessonEntity lessons = lessonService.findByLessonId(lessonId);
			// 購入する商品の情報を保持しておく必要があるため、変数listに購入する商品情報を追加する
			list.add(lessons);
			// カートの内容をセッションで保持していつでも閲覧することができるように
			// 変数listの内容をセットする
			session.setAttribute("cart", list);
			model.addAttribute("cartList", list);
			model.addAttribute("loginFig", loginCheck());
			model.addAttribute("userName",loginUserName());
			return "cart_list.html";
		} else {
			// カートに２個目の商品を入れる場合
			// 既に１個目の商品の情報セッションに保存されているので、そのセッションを呼び出す必要がある
			LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");
			// lessonIDを使用して購入する製品の情報を取得する
			LessonEntity lessons = lessonService.findByLessonId(lessonId);
			// 変数listに新たな商品情報を追加する
			list.add(lessons);
			model.addAttribute("cartList", list);
			model.addAttribute("loginFig", loginCheck());
			model.addAttribute("userName",loginUserName());
			return "cart_list.html";

		}
	}
	
	@GetMapping("/cart/delete/{lessonId}")
	public String getCartDelete(@PathVariable Long lessonId, Model model) {
		LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");
		int idx = 0;
		Iterator<LessonEntity> ite = list.iterator();
		while(ite.hasNext()) {
			LessonEntity lessonEntity = ite.next();
			if(lessonEntity.getLessonId().equals(lessonId)) {
				break;
			}
			idx++;
		}
		list.remove(idx);
		return"redirect:/user/cart/list";
	}
}
