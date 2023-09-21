package CTeam.com.ex.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.AdminDao;
import CTeam.com.ex.models.entity.AdminEntity;

@Service
public class AdminService {
	// AdminDaoクインタフェースを使う
	@Autowired
	private AdminDao adminDao;
	//新しいadminアカウントを作る前存在するかどうか確認
	public boolean beforeCreateAdmin_Confirm(String adminEmail) {
		if(adminDao.findByAdminEmail(adminEmail)==null) {
			return true;
		}
		return false;
	}
	//アカウント情報を保存
	public boolean createAdmin(String adminName, String adminEmail, String adminPassword) {
		//メールアドレスが存在しなかった場合
		if(adminDao.findByAdminEmail(adminEmail)==null) {
			// 現在の日時を取得
			LocalDate currentDate = LocalDate.now();
			//保存
			adminDao.save(new AdminEntity(adminName, adminEmail, adminPassword,0,currentDate));
			return true;
		}
		//存在した場合は失敗
		return false;
	}
	
	//ログイン処理
		public AdminEntity findByAdminNameAndAdminPassword(String adminName, String adminPassword) {
			//adminのメールアドレスをパスワードを条件にDBを検索して取得
			AdminEntity adminEntity = adminDao.findByAdminNameAndAdminPassword(adminName, adminPassword);
			// なかった場合はログイン失敗
			if(adminEntity == null){
		        return null;
		    }else{
		    	// あった場合はログイン成功
		        return adminEntity;
		    }
		}
}
