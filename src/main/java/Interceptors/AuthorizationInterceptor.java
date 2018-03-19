package Interceptors;

import Utils.Constants;
import Utils.CookieUtil;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (HttpMethod.GET.toString().equals(httpServletRequest.getMethod())) {
            if (Constants.ApplicationPages.REGISTER_PAGE.equals(httpServletRequest.getServletPath())) {
                return true;
            } else {
                Cookie[] cookies = httpServletRequest.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (Constants.Cookies.USER_ID_COOKIE_NAME.equals(cookie.getName()) && cookie.getValue().length() > 0) {
                            try {
                                if (Integer.valueOf(cookie.getValue()) > 0) {
                                    CookieUtil.setCookie(cookie, Constants.Cookies.COOKIE_LIFETIME, httpServletResponse);
                                    return true;
                                }
                            } catch (Exception ex) {
                                CookieUtil.setCookie(cookie, Constants.Cookies.COOKIE_EXPIRE_TIME, httpServletResponse);
                                break;
                            }
                        }
                    }
                }
            }
        } else if (HttpMethod.POST.toString().equals(httpServletRequest.getMethod())) {
            return true;
        }
        httpServletResponse.sendRedirect("/");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
