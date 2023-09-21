package CTeam.com.ex.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CTeam.com.ex.models.entity.KeyEntity;
import CTeam.com.ex.models.entity.SubscriptionEntity;

public interface SubscriptionDao extends JpaRepository<SubscriptionEntity,KeyEntity>{
	@Query(value = "select a.transaction_date,a.student_id,a.amount,a.transaction_id,b.lesson_id,c.start_date,c.start_time,c.finish_time,c.lesson_name,c.lesson_detail,c.lesson_fee,c.image_name from transaction_history a join transaction_item b on a.a.transaction_id=b.transaction_id join lesson c on b.lesson_id=c.lesson_id where student_id=?1",nativeQuery = true)
	List<SubscriptionEntity>findByStudentId(Long studentId);

}
