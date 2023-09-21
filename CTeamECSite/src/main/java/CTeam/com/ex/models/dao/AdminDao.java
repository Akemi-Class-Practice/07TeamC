package CTeam.com.ex.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import CTeam.com.ex.models.entity.AdminEntity;

public interface AdminDao extends JpaRepository<AdminEntity, Long>{
	//AdminEntityを保存する
	AdminEntity save(AdminEntity adminEntity);
	// AdminEntityの中からメールアドレス一致するものを検索して返す
	AdminEntity findByAdminEmail(String adminEmail);
	// AdminEntityの中から、そのメールアドレスとパスワードと一致するものを検索して返す
	AdminEntity findByAdminNameAndAdminPassword(String adminName, String adminPassword);
	
}
