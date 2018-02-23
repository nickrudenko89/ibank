package Controllers;

import Daos.UserDao;
import Entities.UserEntity;
import Utils.Constants;
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
    public String autoLogIn(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (Constants.Cookies.USER_ID_COOKIE_NAME.equals(cookie.getName()) && cookie.getValue().length() > 0) {
                //TODO get user from DB
                return "/index";
            }
        }
        return "/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "login", required = false, defaultValue = "nick") String login,
                        @RequestParam(name = "password", required = false, defaultValue = "123456") String password,
                        Model model, HttpServletRequest request, HttpServletResponse response) {
        String contextPath = request.getContextPath();
        UserEntity user = userDao.getUserbyLoginAndPassword(login, password);
        if (user != null) {
            model.addAttribute("name", user.getLogin());
            Cookie newCookie = new Cookie(Constants.Cookies.USER_ID_COOKIE_NAME, String.valueOf(user.getId()));
            newCookie.setMaxAge(20);
            newCookie.setPath(contextPath);
            response.addCookie(newCookie);
        }
        return "/index";
    }

    @RequestMapping("/register")
    public String register() {
        return "/index";
    }
}
