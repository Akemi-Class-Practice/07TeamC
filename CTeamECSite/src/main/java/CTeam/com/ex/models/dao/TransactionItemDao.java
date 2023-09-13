package CTeam.com.ex.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CTeam.com.ex.models.entity.Transaction_ItemEntity;
import jakarta.transaction.Transactional;

public interface TransactionItemDao extends JpaRepository<Transaction_ItemEntity, Long>{
	Transaction_ItemEntity save(Transaction_ItemEntity transactionItemEntity);
	@Transactional
	List<Transaction_ItemEntity> deleteByTransactionId(Long transactionId);
}
