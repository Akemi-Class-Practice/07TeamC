package CTeam.com.ex.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CTeam.com.ex.models.entity.Transaction_ItemEntity;
import jakarta.transaction.Transactional;

public interface TransactionItemDao extends JpaRepository<Transaction_ItemEntity, Long>{
	//Transaction_ItemEntityの内容保存する
	Transaction_ItemEntity save(Transaction_ItemEntity transactionItemEntity);
	//削除
	@Transactional
	List<Transaction_ItemEntity> deleteByTransactionId(Long transactionId);
}
