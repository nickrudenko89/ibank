package Controllers;

import Entities.UserEntity;
import Services.HistoryService;
import Services.UserService;
import Utils.Constants;
import Utils.CookieUtil;
import Utils.RedirectHelper;
import Utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Controller
public class HistoryController {
    @Autowired
    private HistoryService historyService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedirectHelper redirectHelper;

    @RequestMapping("/showHistory")
    public String showHistory(@RequestParam(name = "history_account", defaultValue = "") String account,
                              @RequestParam(name = "history_start_date", required = false, defaultValue = "") String startDate,
                              @RequestParam(name = "history_end_date", required = false, defaultValue = "") String endDate,
                              HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        model.addAttribute("account", account);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            model.addAttribute("history", historyService.showHistory(account, dateFormat.parse(startDate + Constants.DateTime.START_OF_THE_DAY), dateFormat.parse(endDate + Constants.DateTime.END_OF_THE_DAY)));
        } catch (Exception ex) {
            redirectHelper.RedirectToPage("/payments", request, response, model);
        }
        model.addAttribute("path", "/resources/imported_html/history.jsp");
        return "/index";
    }
}
