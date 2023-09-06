package cteam.com.ex.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cteam.com.ex.model.entity.LessonEntity;
import jakarta.transaction.Transactional;

public interface LessonDao extends JpaRepository<LessonEntity,Long>{
	// 指定されたadminIdに基づいてデータベース内のLessonEntityを検索します
	List<LessonEntity> findByadminId(Long adminId);
	
	//LessonEntityのオブジェクトを引数として受け取ってDBに保存と更新します
	LessonEntity save(LessonEntity lessonEntity);
	
	//LessonNameとregisterDateを使用して、LessonEntityのなかから一致するものを検索して返します
	LessonEntity findByLessonName(String LessonName);
	
	// 指定されたlessonIdに基づいて、データベース内のLessonEntityを検索して返します
	LessonEntity findByLessonId(Long lessonId);
	
	//削除
	@Transactional
	List<LessonEntity>deleteByLessonId(Long LessonId);

}
