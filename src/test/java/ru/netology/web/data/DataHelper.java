package ru.netology.web.data;

import lombok.Value;
import lombok.val;

import java.lang.annotation.Annotation;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }



    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static VerificationCode getInvalidVerificationCode() {
        return new VerificationCode("12346");
    }

    public static String getCard1() {
        return new String("5559 0000 0000 0001");
    }

    public static String getCard2() {
        return new String("5559 0000 0000 0002");
    }

    public static String getInvalidCard() {
        return new String("5559 0000 0000 0000");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }
}
