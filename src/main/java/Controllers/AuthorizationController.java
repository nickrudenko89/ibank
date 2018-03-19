package Controllers;

import Entities.UserEntity;
import Services.UserService;
import Utils.Constants;
import Utils.CookieUtil;
import Utils.UserUtil;
import Validators.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizationController {

    @Autowired
    private UserService userService;
    @Autowired
    private RegisterValidator registerValidator;

    @RequestMapping("/")
    public String autoLogIn(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (request.getCookies() != null) {
            Cookie cookie = CookieUtil.getBankCookie(request);
            if (cookie != null) {
                UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
                model.addAttribute("userName", UserUtil.getUserName(user));
                CookieUtil.setCookie(cookie, Constants.Cookies.COOKIE_LIFETIME, response);
                return "/index";
            }
        }
        return "/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam(name = "login", required = false, defaultValue = "nick") String login,
                        @RequestParam(name = "password", required = false, defaultValue = "123456") String password,
                        Model model, HttpServletRequest request, HttpServletResponse response) {
        UserEntity user = userService.logUser(login, password);
        if (user != null) {
            model.addAttribute("userName", UserUtil.getUserName(user));
            Cookie newCookie = new Cookie(Constants.Cookies.USER_ID_COOKIE_NAME, String.valueOf(user.getId()));
            newCookie.setPath(request.getContextPath());
            CookieUtil.setCookie(newCookie, Constants.Cookies.COOKIE_LIFETIME, response);
            return "/index";
        }
        //TODO проверочный код на мэйл
        model.addAttribute("errorMsg", Constants.Errors.INCORRECT_LOGIN_OR_PASSWORD_ERROR);
        return "/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        //RegisterForm registerForm = new RegisterForm();
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegister(UserEntity registerForm, BindingResult result) {
        registerValidator.validate(registerForm, result);
        if (result.hasErrors()) {
            return "/register";
        }
        return "/index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        CookieUtil.setCookie(cookie, Constants.Cookies.COOKIE_EXPIRE_TIME, response);
        session.invalidate();
        return "/login";
    }
}
