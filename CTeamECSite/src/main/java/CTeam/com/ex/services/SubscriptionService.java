package CTeam.com.ex.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.SubscriptionDao;
import CTeam.com.ex.models.entity.SubscriptionEntity;

@Service
public class SubscriptionService {
	@Autowired
	SubscriptionDao subscriptionDao;
	//studentIdを条件にして購入歴史を検索して取得
	public List<SubscriptionEntity>getPurchaseHistory(Long studentId){
		return subscriptionDao.findByStudentId(studentId);
	}

}
