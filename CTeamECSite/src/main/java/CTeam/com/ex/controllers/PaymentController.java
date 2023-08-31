package CTeam.com.ex.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import CTeam.com.ex.services.PaymentService;

@Controller
public class PaymentController {

    @Autowired
    private HttpSession session;

    @Autowired
    private PaymentService paymentService;

    // 显示支付方式选择页面
    @GetMapping("/payment_selection")
    public String showPaymentPage() {
        return "payment_selection.html";
    }

    // 处理支付提交
    @PostMapping("/process")
    public String processPayment(@RequestParam String paymentMethod) {
        // 调用 PaymentService 处理支付
        boolean paymentSuccess = paymentService.processPayment(paymentMethod);
        if (paymentSuccess) {
            // 支付成功，将支付方式存入会话并重定向到确认页面
            session.setAttribute("paymentMethod", paymentMethod);
            return "redirect:/registration_confirmation";
        } else {
            // 支付失败，重定向到支付错误页面
            return "redirect:/payment_error";
        }
    }

    // 显示确认页面
    @GetMapping("/registration_confirmation")
    public String showConfirmationPage(Model model) {
        // 从会话中获取支付方式并添加到模型
        String paymentMethod = (String) session.getAttribute("paymentMethod");
        model.addAttribute("paymentMethod", paymentMethod);
        return "registration_confirmation.html";
    }

    // 显示支付成功页面
    @PostMapping("/payment_success")
    public String showPaymentSuccessPage(Model model) {
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
