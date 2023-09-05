package CTeam.com.ex.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CTeam.com.ex.services.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminRegisterController {

	@Autowired
    private AdminService adminService;
	
	@Autowired
	private HttpSession session;

    @GetMapping("/admin/register")
    public String showRegisterPage() {
        return "admin_registration.html";
    }

    @PostMapping("/admin/register/process")
    public String registerAdmin(@RequestParam String adminName,
                                @RequestParam String adminEmail,
                                @RequestParam String adminPassword) {
        
        if (adminService.beforeCreateAdmin_Confirm(adminEmail)) {
        	session.setAttribute("adminName", adminName);
        	session.setAttribute("adminEmail", adminEmail);
        	session.setAttribute("adminPassword", adminPassword);
        	
            return "admin_register_confirm.html";
        } else {
            return "redirect:/admin/register";
        }
       
    }
    
    @PostMapping("/admin/register/process/confirm")
    public String confirmAdminRegistration(HttpSession session) {
        // Retrieve data from HttpSession
        String adminName = (String) session.getAttribute("adminName");
        String adminEmail = (String) session.getAttribute("adminEmail");
        String adminPassword = (String) session.getAttribute("adminPassword");
        session.setAttribute("adminName", adminName);
        session.setAttribute("adminEmail", adminEmail);
        session.setAttribute("adminPassword", adminPassword);
        
        if (adminService.createAdmin(adminName, adminEmail,adminPassword)) {
            return "redirect:/admin/login"; 
        } else {
            return "redirect:/admin/register";
        }
    }
    
 // ログイン画面を表示
 	 @GetMapping("/admin/login")
 	    public String getadminLoginPage() {
 	    	return "admin_login.html";
 	    }
   

}
