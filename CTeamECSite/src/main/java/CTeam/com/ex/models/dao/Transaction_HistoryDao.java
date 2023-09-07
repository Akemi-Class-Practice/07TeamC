package CTeam.com.ex.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CTeam.com.ex.models.entity.Transaction_HistoryEntity;

// CHEN
public interface Transaction_HistoryDao extends JpaRepository<Transaction_HistoryEntity,Long>{
	
	public Transaction_HistoryEntity findByTransactionId(Long transactionId);
	
	public List<Transaction_HistoryEntity> findByStudentId(Long studentId);

}
