package CTeam.com.ex.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;
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
		// lessonIdを基準でlessonDaoから内容を取得します
		public LessonEntity getLessonPost(Long lessonId) {
			if(lessonId == null) {
				return null;
			}else {
				return lessonDao.findByLessonId(lessonId);
			}
		}
		//更新処理
		public boolean editLesson(Long lessonId , LocalDate startDate, LocalTime startTime, LocalTime finishTime, String lessonName,
				String lessonDetail, int lessonFee, Long adminId) {
			//データベースの内容を取得
			LessonEntity lessonList = lessonDao.findByLessonId(lessonId);
			if(lessonList == null) {
				return false;
			}else {
				lessonList.setStartDate(startDate);
				lessonList.setStartTime(startTime);
				lessonList.setFinishTime(finishTime);
				lessonList.setLessonName(lessonName);
				lessonList.setLessonDetail(lessonDetail);
				lessonList.setLessonFee(lessonFee);
				
				lessonDao.save(lessonList);
				return true;
			}
		}
		//lessonの画像更新処理
		public boolean editImageLesson(Long lessonId ,String imageName, Long adminId) {
			//データベースの内容を取得
			LessonEntity lessonList = lessonDao.findByLessonId(lessonId);
			if(lessonList == null) {
				return false;
			}else {
				lessonList.setImageName(imageName);
				lessonDao.save(lessonList);
				return true;
			}
		}
		
		
		// lessonIdを基づいてlessonDaoから対応の内容を削除します。
		public boolean deleteLesson(Long lessonId) {
			if(lessonId == null) {
				return false;
			}else {
				lessonDao.deleteByLessonId(lessonId);
				return true;
			}
		}
		
		public LessonEntity findByLessonId(Long lessonId) {
			return lessonDao.findByLessonId(lessonId);
		}
		
		//ユーザー側一覧表示
		public List<LessonEntity>findActiveAllLesson(){
			//LocalTime now = LocalTime.now();
			//本日の日付けと時間
			LocalDateTime dateTimeNow = LocalDateTime.now();
			List<LessonEntity>list = lessonDao.findAll();
			List<LessonEntity>reList = new LinkedList<LessonEntity>();
			Iterator<LessonEntity>ite = list.iterator();
			while(ite.hasNext()) {
				LessonEntity lesson = ite.next();
				LocalDateTime localDateTime = LocalDateTime.of(lesson.getStartDate(),lesson.getStartTime());
				if (dateTimeNow.compareTo(localDateTime)>0) {
					
				}else {
					reList.add(lesson);
				}
			}
			return reList;
		}
}
