package Controllers;

import Daos.UserDao;
import Entities.UserEntity;
import Utils.Constants;
import Utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthorizationController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String autoLogIn(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
            model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
            CookieUtil.setCookie(cookie, Constants.Cookies.COOKIE_LIFETIME, response);
            return "/index";
        }
        return "/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "login", required = false, defaultValue = "nick") String login,
                        @RequestParam(name = "password", required = false, defaultValue = "123456") String password,
                        Model model, HttpServletRequest request, HttpServletResponse response) {
        String contextPath = request.getContextPath();
        UserEntity user = userDao.getUserByLoginAndPassword(login, password);
        if (user != null) {
            model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
            Cookie newCookie = new Cookie(Constants.Cookies.USER_ID_COOKIE_NAME, String.valueOf(user.getId()));
            newCookie.setPath(contextPath);
            CookieUtil.setCookie(newCookie, Constants.Cookies.COOKIE_LIFETIME, response);
            return "/index";
        }
        model.addAttribute("errorMsg", Constants.Errors.INCORRECT_LOGIN_OR_PASSWORD_ERROR);
        return "/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "/index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null)
            CookieUtil.setCookie(cookie, Constants.Cookies.COOKIE_EXPIRE_TIME, response);
        return "/login";
    }
}
