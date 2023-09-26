package CTeam.com.ex.controllers;

import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CTeam.com.ex.models.entity.LessonEntity;
import CTeam.com.ex.models.entity.StudentEntity;
import CTeam.com.ex.models.entity.Transaction_HistoryEntity;
import CTeam.com.ex.services.PaymentService;
import CTeam.com.ex.services.TransactionHistoryService;
import CTeam.com.ex.services.TransactionItemService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {

	@Autowired
	private HttpSession session;

	@Autowired
	private TransactionHistoryService transactionHistoryService;
	@Autowired
	private TransactionItemService transactionItemService;

	@Autowired
	private PaymentService paymentService;

	// 支払い処理
	@PostMapping("/process")
	public String processPayment(@RequestParam String paymentMethod, Model model) {
		// 创建集合,保存课程数据
		LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");
		// 合計金額の計算
		double total = 0;
		// 遍历集合
		Iterator<LessonEntity> ite = list.iterator();
		// @hasNext()判断是否有下一个元素
		while (ite.hasNext()) {
			// 有下一个元素的情况下,向下一个元素移动
			LessonEntity element = ite.next();
			total += element.getLessonFee();
		}
		model.addAttribute("list", list);
		model.addAttribute("amount", total);
		session.setAttribute("total", total);
		// 调用 PaymentService 处理支付
		boolean paymentSuccess = paymentService.processPayment(paymentMethod, total);

		// 处理支付结果
		if (paymentSuccess) {
			// 支付成功，将支付方式存入会话并重定向到确认页面
			session.setAttribute("paymentMethod", paymentMethod);
			return "redirect:/registration_confirmation";
//            return "registration_confirmation.html";
		} else {
			// 支付失败，重定向到支付错误页面
			return "redirect:/payment_error";
		}
	}

	// 显示确认页面
	@GetMapping("/registration_confirmation")
	public String showConfirmationPage(Model model) {

		LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");// add.clear

		// 合計金額の計算
		int total = 0;
		// 遍历集合
		Iterator<LessonEntity> ite = list.iterator();
		// @hasNext()判断是否有下一个元素
		while (ite.hasNext()) {
			// 有下一个元素的情况下,向下一个元素移动
			LessonEntity element = ite.next();
			total += element.getLessonFee();
		}
		// transactoin_historyに保存
		StudentEntity student = (StudentEntity) session.getAttribute("student");
		transactionHistoryService.createtransactionHistory(student.getStudentId(), total);

		// student_idを使用して最新のtransaction_idを取得
		Transaction_HistoryEntity lastestTransactions = transactionHistoryService
				.getTransactionId(student.getStudentId());

		// transactoin_itemに保存
		Iterator<LessonEntity> ite2 = list.iterator();
		while (ite2.hasNext()) {
			LessonEntity element = ite2.next();
			transactionItemService.createTransactionHistory(element.getLessonId(),
					lastestTransactions.getTransactionId());
		}

//    	LinkedList<LessonEntity> list2 = (LinkedList<LessonEntity>) session.getAttribute("cart");

		// 从会话中获取支付方式并添加到模型
		String paymentMethod = (String) session.getAttribute("paymentMethod");
		model.addAttribute("paymentMethod", paymentMethod);
		model.addAttribute("list", list);
		model.addAttribute("userName", student.getStudentName());
		model.addAttribute("userEmail", student.getStudentEmail());
		return "registration_confirmation.html";
	}

	// 显示支付成功页面
	@PostMapping("/payment_success")
	public String showPaymentSuccessPage(Model model) {
		LinkedList<LessonEntity> list = (LinkedList<LessonEntity>) session.getAttribute("cart");// add.clear
		list.clear();
		MockCashcard card = paymentService.getBankCard();
		String paymentMethod = (String) session.getAttribute("paymentMethod");
		model.addAttribute("paymentMethod", paymentMethod);
		model.addAttribute("zandaka", card.getMoney()); // 添加余额信息到模型
		return "success.html";
	}

	// 显示支付错误页面
	@GetMapping("/payment_error")
	public String showPaymentErrorPage() {
		return "payment_error.html";
	}
}
