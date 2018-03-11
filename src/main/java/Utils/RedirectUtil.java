package Utils;

import Daos.UserDao;
import Entities.UserEntity;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectUtil {

    public static void RedirectToPage(String page, UserDao userDao, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            response.sendRedirect(page);
        } catch (IOException e) {
            Cookie cookie = CookieUtil.getBankCookie(request, response);
            if (cookie != null) {
                UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
                model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
                model.addAttribute("path", "/resources/imported_html/blank.html");
            }
        }
    }

}
