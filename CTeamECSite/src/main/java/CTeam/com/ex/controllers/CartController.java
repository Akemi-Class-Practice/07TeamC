package CTeam.com.ex.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CTeam.com.ex.models.entity.LessonEntity;
import CTeam.com.ex.models.entity.StudentEntity;
import CTeam.com.ex.services.LessonService;
import CTeam.com.ex.services.StudentService;
import CTeam.com.ex.services.Transaction_HistoryService;
import CTeam.com.ex.services.Transaction_ItemService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private Transaction_HistoryService transactionHistoryService;

	@Autowired
	private Transaction_ItemService transactionItemService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private HttpSession session;

	//private Long Long;

	// 购物车一览 - CHEN
	@GetMapping("/cart/list")
	public String showCartList(@RequestParam Long lessonId,Model model) {
		//StudentEntity student = null;
		//ログインしている人の情報を首都kする
		StudentEntity student = (StudentEntity) session.getAttribute("student");
		//もしログインしている人の情報が存在しなかったらログイン画面に遷移する
		if(student == null) {
			return "redirect:/student/login";
			//もしカートに何も情報がなかった場合（カートに存在する商品が０個の場合）
		}else if(session.getAttribute("cart")==null) {
			//カート情報を格納しておくための変数を作成
			LinkedList<LessonEntity>list = new LinkedList<LessonEntity>();
			//lessonIDを使用して購入する製品の情報を取得する
			LessonEntity lessons = lessonService.findByLessonId(lessonId);
			//購入する商品の情報を保持しておく必要があるため、変数listに購入する商品情報を追加する
			list.add(lessons);
			//カートの内容をセッションで保持していつでも閲覧することができるように
			//変数listの内容をセットする
			session.setAttribute("cart", list);
			model.addAttribute("cartList",list);
			return "cart_list.html";
		}else {
			//カートに２個目の商品を入れる場合
			//既に１個目の商品の情報セッションに保存されているので、そのセッションを呼び出す必要がある
			LinkedList<LessonEntity>list = (LinkedList<LessonEntity>) session.getAttribute("cart");
			//lessonIDを使用して購入する製品の情報を取得する
			LessonEntity lessons = lessonService.findByLessonId(lessonId);
			//変数listに新たな商品情報を追加する
			list.add(lessons);
			model.addAttribute("cartList",list);
			return "cart_list.html";
			
		}
		
	}

}
