package Utils;

public final class Constants {
    public static final class Cookies {
        public static final String USER_ID_COOKIE_NAME = "ibank.userId";
        public static final int COOKIE_LIFETIME = 600;
        public static final int COOKIE_EXPIRE_TIME = 0;
    }

    public static final class ApplicationPages {
        public static final String LOGIN_PAGE = "/login";
        public static final String REGISTER_PAGE = "/register";
    }

    public static final class Errors {
        public static final String INCORRECT_LOGIN_OR_PASSWORD_ERROR = "Неверный логин или пароль.";
    }

    public static final class Strings {
        public static final String EMPTY_STRING = "";
    }

    public static final class UserProfile {
        public static final int PROFILE_FIELDS_COUNT = 8;
    }
}
