package CTeam.com.ex.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CTeam.com.ex.models.entity.Transaction_ItemEntity;

public interface Transaction_ItemDao extends JpaRepository<Transaction_ItemEntity,Long> {
	
	public List<Transaction_ItemEntity> findByTransactionId(Long transactionId);
	
}
