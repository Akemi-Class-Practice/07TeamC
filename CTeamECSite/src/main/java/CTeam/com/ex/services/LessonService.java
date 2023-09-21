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
	public List<LessonEntity> findAllLessonPost(Long adminId) {
		// なかった場合はnullを返します
		if (adminId == null) {
			return null;
			// あった場合
		} else {
			return lessonDao.findByadminId(adminId);
		}
	}

	// 講座を保存します
	public boolean createLessonPost(LocalDate startDate, LocalTime startTime, LocalTime finishTime, String lessonName,
			String lessonDetail, int lessonFee, String fileName, LocalDate registerDate, Long adminId) {
		// 講座名を基準でDBを検索して取得
		LessonEntity lessonList = lessonDao.findByLessonName(lessonName);
		// 講座名は存在しなかった場合保存する
		if (lessonList == null) {
			lessonDao.save(new LessonEntity(startDate, startTime, finishTime, lessonName, lessonDetail, lessonFee,
					fileName, registerDate, 0, adminId));
			return true;
		} else {
			// 存在した場合失敗
			return false;
		}
	}

	// lessonIdを基準でlessonDaoから内容を取得するメソッド
	public LessonEntity getLessonPost(Long lessonId) {
		if (lessonId == null) {
			return null;
		} else {
			return lessonDao.findByLessonId(lessonId);
		}
	}

	// 更新処理
	public boolean editLesson(Long lessonId, LocalDate startDate, LocalTime startTime, LocalTime finishTime,
			String lessonName, String lessonDetail, int lessonFee, Long adminId) {
		// 講座IDを基準でDBを検索して取得
		LessonEntity lessonList = lessonDao.findByLessonId(lessonId);
		// 講座は存在しなかった場合更新失敗
		if (lessonList == null) {
			return false;
		} else {
			// 存在した場合、データ更新
			lessonList.setStartDate(startDate);
			lessonList.setStartTime(startTime);
			lessonList.setFinishTime(finishTime);
			lessonList.setLessonName(lessonName);
			lessonList.setLessonDetail(lessonDetail);
			lessonList.setLessonFee(lessonFee);
			// 保存する
			lessonDao.save(lessonList);
			return true;
		}
	}

	// lessonの画像更新処理
	public boolean editImageLesson(Long lessonId, String imageName, Long adminId) {
		// 講座IDを基準でDBを検索して取得
		LessonEntity lessonList = lessonDao.findByLessonId(lessonId);
		// 講座は存在しなかった場合更新失敗
		if (lessonList == null) {
			return false;
		} else {
			// 存在した場合、データ更新
			lessonList.setImageName(imageName);
			// 保存する
			lessonDao.save(lessonList);
			return true;
		}
	}

	// lessonIdを基づいてlessonDaoから対応の内容を削除
	public boolean deleteLesson(Long lessonId) {
		if (lessonId == null) {
			return false;
		} else {
			lessonDao.deleteByLessonId(lessonId);
			return true;
		}
	}

	// lessonIdを基づいてlessonDaoから対応の内容を取得
	public LessonEntity findByLessonId(Long lessonId) {
		return lessonDao.findByLessonId(lessonId);
	}

	// ユーザー側一覧表示
	public List<LessonEntity> findActiveAllLesson() {
		// LocalTime now = LocalTime.now();
		// 本日の日付けと時間
		LocalDateTime dateTimeNow = LocalDateTime.now();
		// 全部の講座情報を取得
		List<LessonEntity> list = lessonDao.findAll();
		// 新しい講座リスト
		List<LessonEntity> reList = new LinkedList<LessonEntity>();
		// 集合の要素に順番にアクセスする
		Iterator<LessonEntity> ite = list.iterator();
		// 次の要素があればtrue、なければfalseを返す
		while (ite.hasNext()) {
			LessonEntity lesson = ite.next();
			// 現在の時間より以前の時間の講座は新しいリストに追加しない
			LocalDateTime localDateTime = LocalDateTime.of(lesson.getStartDate(), lesson.getStartTime());
			if (dateTimeNow.compareTo(localDateTime) > 0) {

			} else {
				// 以降の時間の講座は新しいリストに追加
				reList.add(lesson);
			}
		}
		// 新しいリストを返す
		return reList;
	}
}
