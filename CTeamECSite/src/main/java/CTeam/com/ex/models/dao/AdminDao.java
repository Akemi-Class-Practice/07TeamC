package CTeam.com.ex.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import CTeam.com.ex.models.entity.AdminEntity;

public interface AdminDao extends JpaRepository<AdminEntity, Long>{
	
	AdminEntity save(AdminEntity adminEntity);
	
	AdminEntity findByAdminEmail(String adminEmail);
	
}
