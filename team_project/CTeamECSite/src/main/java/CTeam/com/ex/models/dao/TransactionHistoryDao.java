package CTeam.com.ex.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CTeam.com.ex.models.entity.Transaction_HistoryEntity;
import jakarta.transaction.Transactional;

public interface TransactionHistoryDao extends JpaRepository<Transaction_HistoryEntity, Long>{
	Transaction_HistoryEntity save(Transaction_HistoryEntity transactionHistoryEntity);
	@Query(value = "select*from transaction_history where student_id =?1 order by transaction_id desc limit 1",nativeQuery = true)
	Transaction_HistoryEntity findByStudentId(Long studentId);
	@Transactional
	List<Transaction_HistoryEntity> deleteByTransactionId(Long transactionId);
}
