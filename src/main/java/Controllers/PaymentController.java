package Controllers;

import Daos.PaymentDao;
import Daos.UserDao;
import Entities.PaymentEntity;
import Entities.UserEntity;
import Utils.Constants;
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
public class PaymentController {
    @Autowired
    UserDao userDao;
    @Autowired
    PaymentDao paymentDao;

    @RequestMapping("/payments")
    public String showPayments(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
            List<PaymentEntity> payments = paymentDao.getAllPayments();
            model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
            model.addAttribute("clientId", Constants.UserType.BANK_CLIENT);
            model.addAttribute("employeeId", Constants.UserType.BANK_EMPLOYEE);
            model.addAttribute("userType", user.getUserType());
            model.addAttribute("payments", payments);
            model.addAttribute("path", "/resources/imported_html/payments.jsp");
        }
        return "/index";
    }
}
