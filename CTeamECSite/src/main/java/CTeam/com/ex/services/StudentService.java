package CTeam.com.ex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.StudentDao;
import CTeam.com.ex.models.entity.StudentEntity;

@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;
	
	//StudentLogin メソッド
	public StudentEntity loginCheck(String studentEmail, String studentPassword) {
		StudentEntity student = studentDao.findByStudentEmailAndStudentPassword(studentEmail, studentPassword);
		if(student == null) {
			return null;
		}else {
			return student;
		}
	}
		
}
