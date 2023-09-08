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
		if (studentDao.findByStudentName(studentName) == null) {
			LocalDate currentDate = LocalDate.now();
			studentDao.save(new StudentEntity(studentName, studentEmail, studentPassword, 0, currentDate));
			return true;
		} else {
			return false;
		}
	}

	// StudentLogin メソッド
	public StudentEntity loginCheck(String studentEmail, String studentPassword) {
		StudentEntity student = studentDao.findByStudentEmailAndStudentPassword(studentEmail, studentPassword);
		if (student == null) {
			return null;
		} else {
			return student;
		}
	}

}
