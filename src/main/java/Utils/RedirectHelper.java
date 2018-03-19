package Utils;

import Daos.UserDao;
import Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RedirectHelper {

    @Autowired
    UserDao userDao;

    public void RedirectToPage(String page, HttpServletRequest request, HttpServletResponse response, Model model) {
        try {
            response.sendRedirect(page);
        } catch (IOException e) {
            Cookie cookie = CookieUtil.getBankCookie(request);
            UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
            model.addAttribute("userName", UserUtil.getUserName(user));
            model.addAttribute("path", "/resources/imported_html/blank.html");
        }
    }

}
