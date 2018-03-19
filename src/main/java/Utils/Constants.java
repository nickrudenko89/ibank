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
        public static final String CLOSE_ACCOUNT_PAGE = "/closeAccount";
        public static final String OPEN_ACCOUNT_PAGE = "/openAccountProcess";
        public static final String ACCOUNT_OPERATION_QUERY_PAGE = "/accountOperationQuery";
    }

    public static final class Errors {
        public static final String INCORRECT_LOGIN_OR_PASSWORD_ERROR = "Неверный логин или пароль.";
        public static final String ACCOUNT_BALANCE_ERROR = "Закрытие счета невозможно, так как баланс счета не нулевой.";
        public static final String PAYMENT_SUM_ERROR = "Введенная сумма некорректна.";
        public static final String NOT_ENOUGH_MONEY_ERROR = "На счету недостаточно денег для оплаты.";
        public static final String INCORRECT_ACCOUNT_ERROR = "Выберите счет для оплаты.";
    }

    public static final class Messages {
        public static final String ACCOUNT_MOVED_TO_DELETED = "Заявка на удаление счета принята.";
        public static final String ACCOUNT_CREATED = "Заявка на открытие счета принята.";
    }

    public static final class Strings {
        public static final String EMPTY_STRING = "";
        public static final String ONE = "1";
        public static final String TWO = "2";
        public static final String THREE = "3";
    }

    public static final class BankAccount {
        public static final int OPENED_ACCOUNT = 1;
        public static final int ACCOUNT_TO_OPEN = 2;
        public static final int ACCOUNT_TO_CLOSE = 3;
        public static final String[] BANK_CURRENCIES = {"BYN", "USD", "EUR", "RUR"};
        public static final int ACCOUNT_NUMBER_LENGTH = 10;
    }

}
