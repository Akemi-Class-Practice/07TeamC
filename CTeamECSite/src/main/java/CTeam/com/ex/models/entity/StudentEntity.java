package CTeam.com.ex.models.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//tableを指名する
@Table(name = "student")
public class StudentEntity {

	@Id
	// フィールドとテーブルのカラムをマッピングする
	@Column(name = "student_id")
	// プライマリーキーを自動生成する方法を指定する
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long studentId;
	// ユーザー名
	@Column(name = "student_name")
	private String studentName;
	// メールアドレス
	@Column(name = "student_email")
	private String studentEmail;
	// パスワード
	@Column(name = "student_password")
	private String studentPassword;

	// 削除flg
	@Column(name = "delete_flg")
	private int deleteFlg;
	// 登録日付け
	@Column(name = "register_date")
	private LocalDate registerDate;

	// 空のコンストラクタの作成
	public StudentEntity() {
	}

	// 全ての引数を持つコンストラクタの作成
	public StudentEntity(String studentName, String studentEmail, String studentPassword, int deleteFlg,
			LocalDate registerDate) {
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentPassword = studentPassword;
		this.deleteFlg = deleteFlg;
		this.registerDate = registerDate;
	}

	// 全ての引数のgetterとsetter
	public StudentEntity(Long studentId) {
		this.studentId = studentId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
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
