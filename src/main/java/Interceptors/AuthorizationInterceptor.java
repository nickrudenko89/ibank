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
        if (!Constants.ApplicationPages.LOGIN_PAGE.equals(httpServletRequest.getServletPath()) && !Constants.ApplicationPages.REGISTER_PAGE.equals(httpServletRequest.getServletPath())) {
            Cookie cookie = CookieUtil.getBankCookie(httpServletRequest, httpServletResponse);
            if (cookie != null) {
                CookieUtil.setCookie(cookie, Constants.Cookies.COOKIE_LIFETIME, httpServletResponse);
                return true;
            }
        } else {
            if (HttpMethod.POST.toString().equals(httpServletRequest.getMethod())) {
                return true;
            }
        }
        httpServletResponse.sendRedirect("/");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
