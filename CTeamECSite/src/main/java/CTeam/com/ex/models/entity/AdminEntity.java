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
@Table(name = "admin")
public class AdminEntity {
	
	@Id
	//フィールドとテーブルのカラムをマッピングする
	@Column(name = "admin_id")
	//プライマリーキーを自動生成する方法を指定する
	@GeneratedValue(strategy = GenerationType.AUTO)
	//adminId
	private Long adminId;
	//名前
	@Column(name = "admin_name")
	private String adminName;
	//メールアドレス
	@Column(name = "admin_email")
	private String adminEmail;
	//パスワード
	@Column(name = "admin_password")
	private String adminPassword;
	//削除flg
	@Column(name = "delete_flg")
	private int deleteFlg;
	//登録日付け
	@Column(name = "register_date")
	private LocalDate registerDate;
	//空のコンストラクタの作成
    public AdminEntity() {
		
	}
    //全ての引数を持つコンストラクタの作成
	public AdminEntity(String adminName, String adminEmail, String adminPassword, int deleteFlg,
			LocalDate registerDate) {
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.deleteFlg = deleteFlg;
		this.registerDate = registerDate;
	}
    
    //全ての引数のgetterとsetter
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
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
