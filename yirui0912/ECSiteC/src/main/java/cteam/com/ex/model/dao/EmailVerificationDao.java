package cteam.com.ex.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cteam.com.ex.model.entity.EmailVerificationEntity;

public interface EmailVerificationDao extends JpaRepository<EmailVerificationEntity, Long>{
//	EmailVerificationEntity save(EmailVerificationEntity emailVerificationEntity);
	EmailVerificationEntity findByEmail(String email);
}
