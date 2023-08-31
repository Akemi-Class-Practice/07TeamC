package CTeam.com.ex.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.AdminDao;
import CTeam.com.ex.models.entity.AdminEntity;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public boolean beforeCreateAdmin_Confirm(String adminEmail) {
		if(adminDao.findByAdminEmail(adminEmail)==null) {
			return true;
		}
		return false;
	}
	
	public boolean createAdmin(String adminName, String adminEmail, String adminPassword) {
		if(adminDao.findByAdminEmail(adminEmail)==null) {
			LocalDate currentDate = LocalDate.now();
			adminDao.save(new AdminEntity(adminName, adminEmail, adminPassword,0,currentDate));
			return true;
		}
		return false;
	}
}
