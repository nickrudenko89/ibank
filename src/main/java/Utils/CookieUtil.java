package Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    public static void setCookie(Cookie cookie, int cookieLifetime, HttpServletResponse response) {
        cookie.setMaxAge(cookieLifetime);
        response.addCookie(cookie);
    }

    public static Cookie getBankCookie(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie);
        }
        return cookieMap.get(Constants.Cookies.USER_ID_COOKIE_NAME);
    }
}
