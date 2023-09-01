package CTeam.com.ex.services;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.LessonDao;
import CTeam.com.ex.models.entity.LessonEntity;

@Service
public class LessonService {
	// AccountDaoクインタフェースを使います
		@Autowired
		private LessonDao lessonDao;
		
		// すべてのブログ投稿を取得します
		public List<LessonEntity>findAllLessonPost(Long adminId){
			//なかった場合はnullを返します
			if(adminId == null) {
				return null;
				//あった場合
			}else {
				return lessonDao.findByadminId(adminId);
			}
		}
		//ブログ記事を保存します
		public boolean createBlogPost(LocalDate startDate,Time startTime,Time finishTime,
				String lessonName,	String lessonDetail, int lessonFee,String imageName, LocalDate registerDate,
				int deleteFlg,Long adminId) {
			LessonEntity lessonList = lessonDao.findByLessonNameAndregisterDate(lessonName, registerDate);
			if(lessonList == null) {
				lessonDao.save(new LessonEntity(startDate,startTime,finishTime,lessonName,lessonDetail,lessonFee,imageName,registerDate,deleteFlg, adminId));
				return true;
			}else {
				return false;
			}
		}
}
