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
import CTeam.com.ex.services.TransactionHistoryService;
import CTeam.com.ex.services.TransactionItemService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")

@Controller
public class StudentLectureController {
	@Autowired
	LessonService lessonService;

	@Autowired
	HttpSession session;

	@Autowired
	TransactionHistoryService transactionHistoryService;

	@Autowired
	TransactionItemService transactionItemService;

	@Autowired
	SubscriptionService subscriptionService;

	// 講座一覧の画面を取得
	@GetMapping("/menu")
	public String getLessonMenuPage(Model model) {
		// 講座の内容を取得
		List<LessonEntity> lessonList = lessonService.findActiveAllLesson();
		model.addAttribute("lessonList", lessonList);
		// ログインしているかどうか確認する、表示の画面が違う
		model.addAttribute("loginFlg", loginCheck());
		// ログインしている時ユーザーの名前の表示
		model.addAttribute("userName", loginUserName());
		return "user_lecture_list.html";
	}

	// 講座詳細画面を表示処理
	@GetMapping("/lesson/detail/{lessonId}")
	public String getLessonDetailPage(@PathVariable Long lessonId, Model model) {
		// lessonIdを基づいて対応する講座内容を取得
		LessonEntity lesson = lessonService.findByLessonId(lessonId);
		model.addAttribute("lesson", lesson);
		// ログインしているかどうか確認する、表示の画面が違う
		model.addAttribute("loginFlg", loginCheck());
		// ログインしている時ユーザーの名前の表示
		model.addAttribute("userName", loginUserName());
		return "lecture_details.html";
	}

	// 同じ講座はカードに存在しないようにするメソッド
	public boolean isLessonExist(Long lessonId, LinkedList<LessonEntity> list) {
		// 講座を順番にアクセスする
		Iterator<LessonEntity> ite = list.iterator();
		boolean isExist = false;
		while (ite.hasNext()) {
			LessonEntity lesson = ite.next();
			// 同じ講座は存在する場合
			if (lesson.getLessonId().equals(lessonId)) {
				isExist = true;
				// 繰り返し処理終了
				break;
			}
		}
		// isExistを返す
		return isExist;
	}

	// ログインしているユーザーの名前表示
	private String loginUserName() {
		// ログインしている時名前表示
		if (loginCheck() == true) {
			StudentEntity student = (StudentEntity) session.getAttribute("student");
			String studentName = student.getStudentName();
			return studentName;
		} else {
			// ログインしていない時何もない
			return null;
		}
	}

//ログインしているかどうか判断する
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

	// マイページの画面取得
	@GetMapping("/mypage")
	public String getMypage(Model model) {
		// ログインしない場合ログイン画面に飛ぶ
		if (session.getAttribute("student") == null) {
			return login("/user/login");
		} else {
			// ログインした場合ユーザー情報を取得
			StudentEntity student = (StudentEntity) session.getAttribute("student");
			Long studentId = student.getStudentId();
			// 購入履歴取得
			List<SubscriptionEntity> listSub = subscriptionService.getPurchaseHistory(studentId);
			// 購入履歴表示
			model.addAttribute("listSub", listSub);
			// ログインしているかどうか確認
			model.addAttribute("loginFlg", loginCheck());
			// マイページで名前とメールアドレスを表示
			model.addAttribute("userName", student.getStudentName());
			model.addAttribute("userEmail", student.getStudentEmail());
			return "mypage.html";
		}
	}

	// カート一覧
	@PostMapping("/cart/list")
	public String addCartPage(@RequestParam Long lessonId, Model model) {
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
			model.addAttribute("list", list);
			model.addAttribute("loginFlg", loginCheck());
			model.addAttribute("userName", loginUserName());
			return "cart_list.html";
		} else {
			// カートに２個目の商品を入れる場合
			// 既に１個目の商品の情報セッションに保存されているので、そのセッションを呼び出す必要がある
			LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");
			// lessonIDを使用して購入する製品の情報を取得する
			LessonEntity lessons = lessonService.findByLessonId(lessonId);
			// 変数listに新たな商品情報を追加する
			// カートのなか存在している商品は追加されない
			if (isLessonExist(lessonId, list)) {

			} else {
				// カートのなかがない商品追加される
				list.add(lessons);
			}
			// 講座内容の表示
			model.addAttribute("list", list);
			// ログインしているかどうか確認する、表示の画面が違う
			model.addAttribute("loginFlg", loginCheck());
			// ログインしている時ユーザーの名前の表示
			model.addAttribute("userName", loginUserName());
			return "cart_list.html";

		}
	}

	// カート一覧ページの表示
	@GetMapping("/show/cart")
	public String getShowCartPage(Model model) {
		// カードのなか何もない状況
		if (session.getAttribute("cart") == null) {
			// カート情報を格納しておくための変数を作成
			LinkedList<LessonEntity> list = new LinkedList<LessonEntity>();
			model.addAttribute("list", list);
		} else {
			// カードの中商品がある場合、そのセッションを呼び出す必要がある
			LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");
			model.addAttribute("list", list);
		}
		// ログインしているかどうか確認する、表示の画面が違う
		model.addAttribute("loginFlg", loginCheck());
		// ログインしている時ユーザーの名前の表示
		model.addAttribute("userName", loginUserName());
		return "cart_list.html";
	}

	// カード削除機能
	@GetMapping("/cart/delete/{lessonId}")
	public String getCartDelete(@PathVariable Long lessonId, Model model) {
		// カードの中商品があるのでそのセッションを呼び出す必要がある
		LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");
		// リストの一番目から探します
		int idx = 0;
		// 講座を順番にアクセスする
		Iterator<LessonEntity> ite = list.iterator();
		while (ite.hasNext()) {
			LessonEntity lessonEntity = ite.next();
			// 同じ講座があった場合
			if (lessonEntity.getLessonId().equals(lessonId)) {
				// 繰り返し処理終了
				break;
			}
			// 引き続き繰り返し
			idx++;
		}
		// 終了後削除
		list.remove(idx);
		return "redirect:/user/show/cart";
	}

	// 支払い画面
	@GetMapping("/payment_selection")
	public String showPaymentPage(Model model) {
		if (session.getAttribute("cart") != null) {
			LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");
			model.addAttribute("list", list);
			model.addAttribute("loginFlg", loginCheck());
			model.addAttribute("userName", loginUserName());
		} else {
			LinkedList<LessonEntity> list = new LinkedList<LessonEntity>();
			model.addAttribute("list", list);
			model.addAttribute("loginFlg", loginCheck());
			model.addAttribute("userName", loginUserName());
		}
		return "payment_selection.html";
	}

}
