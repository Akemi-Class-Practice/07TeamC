package CTeam.com.ex.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CTeam.com.ex.models.entity.StudentEntity;

public interface StudentDao extends JpaRepository<StudentEntity,Long>{
	//データを保存するinsert文に該当するメソッドを書く
	//StudentEntityを引数として受け取ってStudentEntityの内容を保存した結果を返す
	StudentEntity save(StudentEntity studentEntity);
	
	//studentName
	StudentEntity findByStudentName(String studentName);
	
	StudentEntity findByStudentEmail(String studentEmail);
	//studentName studentPassword
	StudentEntity findByStudentEmailAndStudentPassword(String studentEmail,String studentPassword);

	//findAll 一覧
	List<StudentEntity> findAll();
}
