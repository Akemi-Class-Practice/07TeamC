package CTeam.com.ex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.Transaction_HistoryDao;
import CTeam.com.ex.models.entity.Transaction_HistoryEntity;

@Service
public class Transaction_HistoryService {
	
	@Autowired
	private Transaction_HistoryDao transactionHistoryDao;
	
	public List<Transaction_HistoryEntity> getTransactionHistoryByStudentId(Long studentId){
		return transactionHistoryDao.findByStudentId(studentId);
	}

}
