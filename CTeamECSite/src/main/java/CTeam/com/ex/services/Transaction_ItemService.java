package CTeam.com.ex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.Transaction_ItemDao;
import CTeam.com.ex.models.entity.Transaction_ItemEntity;

@Service
public class Transaction_ItemService {
	
	@Autowired
	private Transaction_ItemDao transactionItemDao;
	
	public List<Transaction_ItemEntity> getTransactionItemsByTransactionId(Long transactionId){
		return transactionItemDao.findByTransactionId(transactionId);
	}

}
