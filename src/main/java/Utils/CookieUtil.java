package Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void setCookie(Cookie cookie, int cookieLifetime, HttpServletResponse response) {
        cookie.setMaxAge(cookieLifetime);
        response.addCookie(cookie);
    }

    public static Cookie getBankCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constants.Cookies.USER_ID_COOKIE_NAME.equals(cookie.getName()) && cookie.getValue().length() > 0) {
                    try {
                        if (Integer.valueOf(cookie.getValue()) > 0)
                            return cookie;
                    } catch (Exception ex) {
                        setCookie(cookie, Constants.Cookies.COOKIE_EXPIRE_TIME, response);
                        break;
                    }
                }
            }
        }
        return null;
    }
}
