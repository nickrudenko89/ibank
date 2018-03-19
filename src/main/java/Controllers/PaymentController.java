package Controllers;

import Daos.AccountDao;
import Daos.HistoryDao;
import Daos.PaymentDao;
import Daos.UserDao;
import Entities.AccountEntity;
import Entities.HistoryEntity;
import Entities.PaymentEntity;
import Entities.UserEntity;
import Enums.UserTypeEnum;
import Services.PaymentService;
import Services.UserService;
import Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class PaymentController {
//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private PaymentDao paymentDao;
//    @Autowired
//    private AccountDao accountDao;
//    @Autowired
//    private HistoryDao historyDao;
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private RedirectHelper redirectHelper;

    @RequestMapping("/payments")
    public String showPayments(HttpServletRequest request, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        switch (userService.getUserTypeByIndex(user.getUserType())) {
            case CLIENT:
                model.addAttribute("url", "/doPayment?action=pay");
                break;
            case EMPLOYEE:
                model.addAttribute("url", "/doPayment?action=edit");
                break;
        }
        model.addAttribute("clientId", UserTypeEnum.CLIENT.getIndex());
        model.addAttribute("userType", user.getUserType());
        model.addAttribute("payments", paymentService.getAllPayments());
        model.addAttribute("path", "/resources/imported_html/payments.jsp");
        return "/index";
    }
    /* TODO
    @RequestMapping("/doPayment")
    public String doPayment(@RequestParam(name = "action", required = false, defaultValue = "") String paymentAction,
                            @RequestParam(name = "id", required = false, defaultValue = "0") int paymentId,
                            @RequestParam(name = "error", required = false, defaultValue = "") String paymentErrorId,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        int userType;
        String jspUrl;
        String requestUri;
        String paymentError;
        requestUri = UriUtil.editUri(request);
        if ("pay".equals(paymentAction)) {
            jspUrl = "/resources/imported_html/do_payment.jsp";
            userType = Constants.UserType.BANK_CLIENT;
        } else if ("edit".equals(paymentAction)) {
            jspUrl = "/resources/imported_html/edit_payment.jsp";
            userType = Constants.UserType.BANK_EMPLOYEE;
        } else {
            redirectHelper.RedirectToPage("/payments", request, response, model);
            return "/index";
        }
        paymentError = paymentService.getPaymentErrorById(paymentErrorId);
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        if (user.getUserType() == userType) {
            PaymentEntity payment = paymentDao.getPaymentById(paymentId);
            if (payment != null) {
                if (user.getUserType() == Constants.UserType.BANK_CLIENT) {
                    model.addAttribute("accounts", user.getAccounts());
                    model.addAttribute("openedAccountStatus", Constants.BankAccount.OPENED_ACCOUNT);
                    model.addAttribute("requestUri", requestUri);
                    model.addAttribute("error", paymentError);
                }
                model.addAttribute("payment", payment);
                model.addAttribute("path", jspUrl);
                return "/index";
            }
        }
        redirectHelper.RedirectToPage("/payments", request, response, model);
        return "/index";
    }

    @RequestMapping("/editPaymentConfirm")
    public String confirmChangesInPayment(@RequestParam(name = "payment_id", required = false, defaultValue = "0") int paymentId,
                                          @RequestParam(name = "payment_type", required = false, defaultValue = "") String paymentType,
                                          HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        if (user.getUserType() == Constants.UserType.BANK_EMPLOYEE) {
            if (!Constants.Strings.EMPTY_STRING.equals(paymentType)) {
                PaymentEntity payment = paymentDao.getPaymentById(paymentId);
                payment.setType(paymentType);
                paymentDao.update(payment);
            }
        }
        redirectHelper.RedirectToPage("/payments", request, response, model);
        return "/index";
    }

    @RequestMapping("/paymentConfirm")
    public String paymentConfirm(@RequestParam(name = "payment_id", required = false, defaultValue = "0") int paymentId,
                                 @RequestParam(name = "payment_account", required = false, defaultValue = "") String paymentAccount,
                                 @RequestParam(name = "payment_sum", required = false, defaultValue = "0") float paymentSum,
                                 HttpServletRequest request, HttpServletResponse response, Model model) {
        AccountEntity account = accountDao.getAccountByAccountNumber(paymentAccount);
        if (account != null) {
            account.setBalance(account.getBalance() - paymentSum);
            accountDao.update(account);
            HistoryEntity historyEntity = new HistoryEntity();
            historyEntity.setAccount(account);
            historyEntity.setPayment(paymentDao.getPaymentById(paymentId));
            historyEntity.setDate(new Date());
            historyEntity.setSum(paymentSum);
            historyDao.save(historyEntity);
        }
        redirectHelper.RedirectToPage("/payments", request, response, model);
        return "/index";
    }

    @RequestMapping("/paymentCancel")
    public String paymentCancel(HttpServletRequest request, HttpServletResponse response, Model model) {
        redirectHelper.RedirectToPage("/payments", request, response, model);
        return "/index";
    }
*/
}