package cteam.com.ex.service;

import java.util.Random;

//認証コート作成メソッド
public class VerificationCodeGenerator {
    public String generateVerificationCode() {
        Random random = new Random();
        int min = 1000;
        int max = 9999;
        int verificationCode = random.nextInt(max - min + 1) + min;
        return String.valueOf(verificationCode);
    }

}
