package cteam.com.ex.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cteam.com.ex.model.dao.AdminDao;
import cteam.com.ex.model.entity.AdminEntity;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	public boolean beforeCreateAdmin_Confirm(String adminEmail) {
		if(adminDao.findByAdminEmail(adminEmail)==null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean createAdmin(String adminName, String adminEmail, String adminPassword) {
		if(adminDao.findByAdminEmail(adminEmail)==null) {
			LocalDate currentDate = LocalDate.now();
			adminDao.save(new AdminEntity(adminName, adminEmail, adminPassword,0,currentDate));
			return true;
		}
		else {
			return false;
		}
	}
	
}
