package cteam.com.ex.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cteam.com.ex.model.dao.LessonDao;
import cteam.com.ex.model.entity.LessonEntity;

@Service
public class LessonService {
	@Autowired
	private LessonDao lessonDao;

	// すべての講座を取得します
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
	
	public LessonEntity findByLessonId(Long lessonId) {
		return lessonDao.findByLessonId(lessonId);
	}
	
	//lesson更新処理
	public boolean editLesson(Long lessonId , LocalDate startDate, LocalTime startTime, LocalTime finishTime, String lessonName,
			String lessonDetail, int lessonFee,String imageName, Long adminId) {
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
			lessonList.setImageName(imageName);
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
	
	//講座削除処理
	public boolean deleteLesson(Long lessonId) {
		if(lessonId != null) {
			lessonDao.deleteByLessonId(lessonId);
			return true;
		}else {
			return false;
		}
	}
}
