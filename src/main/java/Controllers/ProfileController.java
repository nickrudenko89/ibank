package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ProfileController {
    @RequestMapping("/profile")
    public String showProfile(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("path","/resources/imported_html/profile.html");
        return "/index";
    }

    @RequestMapping("/editProfile")
    public String editProfile(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/index";
    }

    @RequestMapping("/saveProfile")
    public String saveProfile(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/index";
    }

    @RequestMapping("/cancelProfile")
    public String cancelChangesInProfile(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "/index";
    }
}
