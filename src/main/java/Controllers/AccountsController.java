package Controllers;

import Daos.AccountDao;
import Entities.AccountEntity;
import Utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AccountsController {
    @Autowired
    private AccountDao accountDao;

    @RequestMapping("/accounts")
    public String showAccounts(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            List<AccountEntity> accounts = accountDao.getAccountsByUserId(Integer.valueOf(cookie.getValue()));
            int a=10;
        }
        return "/index";
    }
}
