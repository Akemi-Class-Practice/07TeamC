package CTeam.com.ex.models.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity {
	
	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long studentId;
	
	@Column(name = "student_name")
	private String studentName;
	
	@Column(name = "student_email")
	private String studentEmail;
	
	@Column(name = "student_password")
	private String studentPassword;
	
	//0と1の　2つの状態だけある
	@Column(name = "delete_flg")
	private int deleteFlg;
	
	@Column(name = "register_date")
	private LocalDate registerDate;

	public StudentEntity(long studentId, String studentName, String studentEmail, String studentPassword, int deleteFlg,
			LocalDate registerDate) {
		
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		this.deleteFlg = deleteFlg;
		this.registerDate = registerDate;
	}

	public StudentEntity(long studentId) {
		
		this.studentId = studentId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public int getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(int deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}
	
	
	
	
}
