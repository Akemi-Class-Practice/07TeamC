package CTeam.com.ex.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.TransactionHistoryDao;
import CTeam.com.ex.models.entity.Transaction_HistoryEntity;

@Service
public class TransactionHistoryService {
	@Autowired
	private TransactionHistoryDao transactionHistoryDao;
	public void createtransactionHistory(Long studentId,int amount) {
		LocalDateTime transactionDao = LocalDateTime.now();
		transactionHistoryDao.save(new Transaction_HistoryEntity(studentId,amount,transactionDao));
	}
    public Transaction_HistoryEntity getTransactionId(Long studentId) {
    	return transactionHistoryDao.findByStudentId(studentId);
    }
    public List<Transaction_HistoryEntity> deleteTransactionId(Long transactionId) {
    	return transactionHistoryDao.deleteByTransactionId(transactionId);
    }
}
