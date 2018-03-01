package Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void setCookie(Cookie cookie, int cookieLifetime, HttpServletResponse response) {
        cookie.setMaxAge(cookieLifetime);
        response.addCookie(cookie);
    }
}
