package cteam.com.ex.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cteam.com.ex.model.entity.AdminEntity;

public interface AdminDao extends JpaRepository<AdminEntity, Long>{

	AdminEntity save(AdminEntity adminEntity);
	
	AdminEntity findByAdminEmail(String adminEmail);
}
