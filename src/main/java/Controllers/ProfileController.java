package Controllers;

import Entities.ProfileEntity;
import Entities.UserEntity;
import Forms.ChangeProfileForm;
import Services.ProfileService;
import Services.UserService;
import Utils.CookieUtil;
import Utils.RedirectHelper;
import Utils.UserUtil;
import Validators.ChangeProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private RedirectHelper redirectHelper;
    @Autowired
    private ChangeProfileValidator changeProfileValidator;


    @RequestMapping("/profile")
    public String showProfile(HttpServletRequest request, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        ProfileEntity profile = profileService.getUserProfile(Integer.valueOf(cookie.getValue()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        model.addAttribute("profile", profile);
        model.addAttribute("userName", profile.getFirstName() + " " + profile.getLastName());
        model.addAttribute("birthDate", dateFormat.format(profile.getBirthDate()));
        model.addAttribute("path", "/resources/imported_html/profile.jsp");
        return "/index";
    }

    @RequestMapping("/editProfile")
    public String editProfile(HttpServletRequest request, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        model.addAttribute("changeProfileForm", UserUtil.setUserToChangeProfileForm(user));
        model.addAttribute("path", "/resources/imported_html/edit_profile.jsp");
        return "/index";
    }

    @RequestMapping("/saveProfile")
    public String saveProfile(HttpServletRequest request, HttpServletResponse response, Model model, ChangeProfileForm changeProfileForm, BindingResult result) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        changeProfileValidator.validate(changeProfileForm, result);
        if (result.hasErrors()) {
            model.addAttribute("userName", UserUtil.getUserName(user));
            model.addAttribute("path", "/resources/imported_html/edit_profile.jsp");
            return "/index";
        }
        userService.saveChangesToProfile(changeProfileForm, user);
        redirectHelper.RedirectToPage("/profile", request, response, model);
        return "/index";
    }

    @RequestMapping("/cancelProfile")
    public String cancelChangesInProfile(HttpServletRequest request, HttpServletResponse response, Model model) {
        redirectHelper.RedirectToPage("/profile", request, response, model);
        return "/index";
    }
}
