package Interceptors;

import Utils.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if(!Constants.ApplicationPages.LOGIN_PAGE.equals(httpServletRequest.getServletPath()) && !Constants.ApplicationPages.REGISTER_PAGE.equals(httpServletRequest.getServletPath())) {
            Cookie[] cookies = httpServletRequest.getCookies();
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (Constants.Cookies.USER_ID_COOKIE_NAME.equals(cookie.getName()) && cookie.getValue().length() > 0) {
                    return true;
                }
            }
        }
        else {
            if(Constants.RequestMethods.POST_METHOD.equals(httpServletRequest.getMethod())) {
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
