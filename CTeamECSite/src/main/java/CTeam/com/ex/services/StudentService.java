package CTeam.com.ex.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.StudentDao;
import CTeam.com.ex.models.entity.StudentEntity;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	// 登録処理用メソッド
	public boolean creatStudent(String studentName, String studentEmail, String studentPassword) {
		// 名前は存在しなかった場合
		if (studentDao.findByStudentName(studentName) == null) {
			// 登録時間
			LocalDate currentDate = LocalDate.now();
			// 保存する
			studentDao.save(new StudentEntity(studentName, studentEmail, studentPassword, 0, currentDate));
			return true;
		} else {
			// 名前は存在したばいは失敗
			return false;
		}
	}

	// StudentLogin メソッド
	public StudentEntity loginCheck(String studentEmail, String studentPassword) {
		// メールアドレスをパスワードを条件にDBを検索して取得
		StudentEntity student = studentDao.findByStudentEmailAndStudentPassword(studentEmail, studentPassword);
		// 存在しなかった場合nullを返す
		if (student == null) {
			return null;
		} else {
			// 存在した場合情報を返す
			return student;
		}
	}

	// studentIdを基づいてstudentDaoから対応の内容を取得
	public StudentEntity findByStudentId(Long studentId) {
		return studentDao.findByStudentId(studentId);
	}

	// StudentPassword 更新メソッド
	public void updatePassword(String studentEmail, String studentPassword) {
		// メールアドレスを条件にDBを検索して取得
		StudentEntity student = studentDao.findByStudentEmail(studentEmail);
		// パスワードを更新する
		student.setStudentPassword(studentPassword);
		// 保存する
		studentDao.save(student);
	}

}
