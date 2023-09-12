package cteam.com.ex.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import cteam.com.ex.model.dao.EmailVerificationDao;
import cteam.com.ex.model.entity.EmailVerificationEntity;

@Service
public class EmailVerificationService {
	@Autowired
    private final EmailVerificationDao emailVerificationDao;

    @Autowired
    public EmailVerificationService(EmailVerificationDao emailVerificationDao) {
        this.emailVerificationDao = emailVerificationDao;
    }
    
    @Autowired
    private JavaMailSender javaMailSender;

    public EmailVerificationEntity generateVerificationCode(String email) {
        //メールに送るコード
        String verificationCode = generateRandomCode();

        EmailVerificationEntity verificationEntity = new EmailVerificationEntity();
        verificationEntity.setEmail(email);
        verificationEntity.setVerificationCode(verificationCode);
//        verificationEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        return emailVerificationDao.save(verificationEntity);
    }

    public boolean verifyCode(String email, String code) {
        EmailVerificationEntity verificationEntity = emailVerificationDao.findByEmail(email);

        if (verificationEntity != null && verificationEntity.getVerificationCode().equals(code)) {
            // 
            emailVerificationDao.delete(verificationEntity);
            return true;
        }
        return false;
    }
    
    //メールを送るサービス
    public void sendVerificationCodeEmail(String email, String verificationCode) {
        String subject = "認証コード";
        String text = "認証コード：" + verificationCode 
        		+ "パスワードリセットのため、この番号をリセット画面に入力してください。";

        //メール内容を作成
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("441662587@qq.com"); // 送信元
        message.setTo(email);//送信先
        message.setSubject(subject);//メールテーマ
        message.setText(text);//内容

        javaMailSender.send(message);

    }
    
    //メールに送るコード作成サービス
    private String generateRandomCode() {
    	VerificationCodeGenerator a = new VerificationCodeGenerator();
		return a.generateVerificationCode();
    }
    
//    public EmailVerificationEntity getById(String eMail) {
//    	return emailVerificationDao.findByEmail(eMail);
//    }
//    
//    //パスワード更新サービス
//    public void updatePassword(String email,String password) {
//    	
//    }
}