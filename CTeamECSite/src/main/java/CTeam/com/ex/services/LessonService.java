package CTeam.com.ex.services;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
		
		//講座を保存します
		public boolean createLessonPost(LocalDate startDate,LocalTime startTime,LocalTime finishTime,
				String lessonName,	String lessonDetail, int lessonFee,String fileName, LocalDate registerDate,
				Long adminId) {
			LessonEntity lessonList = lessonDao.findByLessonName(lessonName);
			if(lessonList == null) {
				lessonDao.save(new LessonEntity(startDate,startTime,finishTime,lessonName,lessonDetail,lessonFee,fileName,registerDate,0, adminId));
				return true;
			}else {
				return false;
			}
		}
		
}
