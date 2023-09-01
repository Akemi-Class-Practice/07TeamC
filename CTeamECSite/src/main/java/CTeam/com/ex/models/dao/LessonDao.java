package CTeam.com.ex.models.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CTeam.com.ex.models.entity.LessonEntity;
import jakarta.transaction.Transactional;

public interface LessonDao  extends JpaRepository<LessonEntity,Long>{
	// 指定されたadminIdに基づいてデータベース内のLessonEntityを検索します
	List<LessonEntity> findByadminId(Long adminId);
	
	//LessonEntityのオブジェクトを引数として受け取ってDBに保存と更新します
	LessonEntity save(LessonEntity lessonentity);
	
	//LessonNameとregisterDateを使用して、LessonEntityのなかから一致するものを検索して返します
	LessonEntity findByLessonNameAndregisterDate(String LessonName,LocalDate registerDate);
	
	//削除
	@Transactional
	List<LessonEntity>deleteByLessonId(Long LessonId);
	
}
