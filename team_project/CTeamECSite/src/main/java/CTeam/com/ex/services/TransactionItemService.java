package CTeam.com.ex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.TransactionItemDao;
import CTeam.com.ex.models.entity.Transaction_ItemEntity;

@Service
public class TransactionItemService {
	@Autowired
	private TransactionItemDao transactionItemDao;
	
	public void createTransactionHistory(Long transactionId,Long lessonId) {
		transactionItemDao.save(new Transaction_ItemEntity(transactionId,lessonId));
	}
	
	public List<Transaction_ItemEntity>deleteTransactionId(Long transactionId){
		return transactionItemDao.deleteByTransactionId(transactionId);
	}

}
