package data;


import lombok.Value;

public class DataHelper {

    private DataHelper(){}

    @Value
    public static class AuthInfo {
        private static String login;
        private static String password;
    }

    public static AuthInfo getAuthInfo() {return new AuthInfo("vasya", "qwerty123"); }

    public static AuthInfo getOtherAuthInfo(AuthInfo) {return new AuthInfo()("petya", "123qwerty"); }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo) {return new VerificationCode("1234"); }

}
