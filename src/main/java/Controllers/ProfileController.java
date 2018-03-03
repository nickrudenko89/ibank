package Controllers;

import Daos.UserDao;
import Entities.UserEntity;
import Services.UserService;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Controller
public class ProfileController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @RequestMapping("/profile")
    public String showProfile(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            model.addAttribute("user", user);
            model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
            model.addAttribute("birthDate", dateFormat.format(user.getProfile().getBirthDate()));
            model.addAttribute("path", "/resources/imported_html/profile.jsp");
            return "/index";
        }
        return "/login";
    }

    @RequestMapping("/editProfile")
    public String editProfile(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList<Boolean> checkedFields = new ArrayList<Boolean>();
            for (int counter = 0; counter < Constants.UserProfile.PROFILE_FIELDS_COUNT; counter++)
                checkedFields.add(true);
            model.addAttribute("checkedFields", checkedFields);
            model.addAttribute("user", user);
            model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
            model.addAttribute("birthDate", dateFormat.format(user.getProfile().getBirthDate()));
            model.addAttribute("path", "/resources/imported_html/edit_profile.jsp");
            return "/index";
        }
        return "/login";
    }

    @RequestMapping("/saveProfile")
    public String saveProfile(@RequestParam(name = "password", required = false, defaultValue = "") String password,
                              @RequestParam(name = "password_confirm", required = false, defaultValue = "") String passwordConfirm,
                              @RequestParam(name = "first_name", required = false, defaultValue = "") String firstName,
                              @RequestParam(name = "last_name", required = false, defaultValue = "") String lastName,
                              @RequestParam(name = "birth_date", required = false, defaultValue = "") String birthDate,
                              @RequestParam(name = "passport_number", required = false, defaultValue = "") String passportNumber,
                              @RequestParam(name = "address", required = false, defaultValue = "") String address,
                              @RequestParam(name = "telephone_number", required = false, defaultValue = "") String telephoneNumber,
                              @RequestParam(name = "email", required = false, defaultValue = "") String email,
                              HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
            if (!userService.checkNewProfile(password, passwordConfirm, firstName, lastName, birthDate, passportNumber, address, telephoneNumber, email, model)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                model.addAttribute("user", user);
                model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
                model.addAttribute("birthDate", dateFormat.format(user.getProfile().getBirthDate()));
                model.addAttribute("path", "/resources/imported_html/edit_profile.jsp");
                return "/index";
            }
            userService.changePassword(user, password, passwordConfirm);
            userService.changeField(user, user.getProfile().getFirstName(), firstName, 1);
            userService.changeField(user, user.getProfile().getLastName(), lastName, 2);
            userService.changeField(user, user.getProfile().getBirthDate().toString().split(" ")[0], birthDate, 3);
            userService.changeField(user, user.getProfile().getPassportNumber(), passportNumber, 4);
            userService.changeField(user, user.getProfile().getAddress(), address, 5);
            userService.changeField(user, user.getProfile().getTelephoneNumber(), telephoneNumber, 6);
            userService.changeField(user, user.getProfile().getEmail(), email, 7);
            userDao.update(user);
            try {
                response.sendRedirect("/profile");
            } catch (IOException e) {
                model.addAttribute("path", "/resources/imported_html/blank.html");
                return "/index";
            }
        }
        return "/login";
    }

    @RequestMapping("/cancelProfile")
    public String cancelChangesInProfile(HttpServletResponse response, Model model) {
        try {
            response.sendRedirect("/profile");
        } catch (IOException e) {
            model.addAttribute("path", "/resources/imported_html/blank.html");
        }
        return "/index";
    }
}
