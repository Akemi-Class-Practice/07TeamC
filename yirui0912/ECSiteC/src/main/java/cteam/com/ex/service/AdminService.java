package cteam.com.ex.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cteam.com.ex.model.dao.AdminDao;
import cteam.com.ex.model.entity.AdminEntity;
import cteam.com.ex.model.entity.StudentEntity;

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
	
	//ログイン処理
	public AdminEntity findByAdminNameAndAdminPassword(String adminName, String adminPassword) {
		AdminEntity adminEntity = adminDao.findByAdminNameAndAdminPassword(adminName, adminPassword);
	    if(adminEntity == null){
	        return null;
	    }else{
	        return adminEntity;
	    }
	}
	
	public AdminEntity findByAdminId(Long adminId) {
		return adminDao.findByAdminId(adminId);
	}
	
	//AdminPassword 更新メソッド
	public void updatePassword(String adminEmail, String studentPassword) {
		AdminEntity admin = adminDao.findByAdminEmail(adminEmail);
		admin.setAdminPassword(studentPassword);
		adminDao.save(admin);
	}
	
}
