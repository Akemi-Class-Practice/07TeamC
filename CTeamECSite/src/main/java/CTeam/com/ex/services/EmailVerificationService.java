package CTeam.com.ex.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import CTeam.com.ex.models.dao.EmailVerificationDao;
import CTeam.com.ex.models.entity.EmailVerificationEntity;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;

import com.google.code.kaptcha.Producer; 
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.awt.image.BufferedImage;
import java.sql.Timestamp;
import java.util.Properties;

@Service
public class EmailVerificationService {

    private final EmailVerificationDao emailVerificationDao;

    @Autowired
    public EmailVerificationService(EmailVerificationDao emailVerificationDao) {
        this.emailVerificationDao = emailVerificationDao;
    }
    
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Autowired
    private HttpSession httpSession;

    private Producer captchaProducer;

    public EmailVerificationEntity generateVerificationCode(String email) {
        // 
        String verificationCode = generateRandomCode();

        // 
        EmailVerificationEntity verificationEntity = new EmailVerificationEntity();
        verificationEntity.setEmail(email);
        verificationEntity.setVerificationCode(verificationCode);
        verificationEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // 
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
    
    public void sendVerificationCodeEmail(String email, String verificationCode) {
        String subject = "認証コード:";
        String text = "パスワードリセットのため、あなたの認証コードは：" + verificationCode;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("441662587@qq.com"); // 设置发送者地址为您的邮箱地址
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);

    }

    private String generateRandomCode() {
    	VerificationCodeGenerator a = new VerificationCodeGenerator();
		return a.generateVerificationCode();
    }
    
    @PostConstruct
    public void init() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        // 这里可以设置Kaptcha的属性，如图片大小、字符长度等
        // 示例设置：
        kaptcha.setConfig(new Config(new Properties()));
        this.captchaProducer = kaptcha;
    }

    public BufferedImage generateCaptchaImage() {
        String captchaText = captchaProducer.createText();
        httpSession.setAttribute("captcha", captchaText);
        return captchaProducer.createImage(captchaText);
    }

    public boolean verifyCaptcha(String userInput) {
        String storedCaptcha = (String) httpSession.getAttribute("captcha");
        return storedCaptcha != null && storedCaptcha.equals(userInput);
    }
}