package Controllers;

import Entities.AccountEntity;
import Entities.HistoryEntity;
import Entities.PaymentEntity;
import Entities.UserEntity;
import Enums.PaymentActionEnum;
import Enums.UserTypeEnum;
import Forms.MakePaymentForm;
import Services.AccountService;
import Services.HistoryService;
import Services.PaymentService;
import Services.UserService;
import Utils.*;
import Validators.MakePaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

@Controller
public class PaymentController {
    @Autowired
    private UserService userService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private RedirectHelper redirectHelper;
    @Autowired
    private MakePaymentValidator makePaymentValidator;

    @RequestMapping("/payments")
    public String showPayments(HttpServletRequest request, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        switch (userService.getUserTypeByIndex(user.getUserType())) {
            case CLIENT:
                model.addAttribute("url", "/doPayment?action=pay");
                model.addAttribute("accounts", user.getAccounts());
                model.addAttribute("openedAccountStatus", Constants.BankAccount.OPENED_ACCOUNT);
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

    @RequestMapping("/doPayment")
    public String doPayment(@RequestParam(name = "action", required = false, defaultValue = "") String paymentAction,
                            @RequestParam(name = "id", required = false, defaultValue = "0") int paymentId,
                            HttpServletRequest request, HttpServletResponse response, Model model) {
        UserTypeEnum userType = UserTypeEnum.CLIENT;
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        switch (PaymentActionEnum.valueOf(paymentAction.toUpperCase())) {
            case PAY:
                model.addAttribute("path", "/resources/imported_html/do_payment.jsp");
                break;
            case EDIT:
                model.addAttribute("path", "/resources/imported_html/edit_payment.jsp");
                userType = UserTypeEnum.EMPLOYEE;
                break;
        }
        model.addAttribute("userName", UserUtil.getUserName(user));
        if (user.getUserType() == userType.getIndex()) {
            PaymentEntity payment = paymentService.getPaymentById(paymentId);
            if (user.getUserType() == UserTypeEnum.CLIENT.getIndex()) {
                model.addAttribute("accounts", user.getAccounts());
                model.addAttribute("openedAccountStatus", Constants.BankAccount.OPENED_ACCOUNT);
                model.addAttribute("makePaymentForm", PaymentUtil.setPaymentToForm(payment));
            } else {
                model.addAttribute("payment", payment);
            }
            return "/index";
        }
        redirectHelper.RedirectToPage("/payments", request, response, model);
        return "/index";
    }

    @RequestMapping("/paymentConfirm")
    public String paymentConfirm(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session, MakePaymentForm makePaymentForm, BindingResult result) {
        makePaymentValidator.validate(makePaymentForm, result);
        if (result.hasErrors()) {
            Cookie cookie = CookieUtil.getBankCookie(request);
            UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
            model.addAttribute("userName", UserUtil.getUserName(user));
            PaymentEntity payment = paymentService.getPaymentById(makePaymentForm.getId());
            makePaymentForm.setType(payment.getType());
            model.addAttribute("accounts", user.getAccounts());
            model.addAttribute("openedAccountStatus", Constants.BankAccount.OPENED_ACCOUNT);
            model.addAttribute("path", "/resources/imported_html/do_payment.jsp");
            return "/index";

        }
        AccountEntity account = accountService.getUserAccountByAccountNumber(makePaymentForm.getAccount());
        accountService.makePayment(account, makePaymentForm.getSum());
        HistoryEntity cheque = historyService.savePaymentToHistory(account, makePaymentForm);
        session.setAttribute("account", account);
        session.setAttribute("cheque", cheque);
        redirectHelper.RedirectToPage("/cheque", request, response, model);
        return "/index";
    }

    @RequestMapping("/cheque")
    public String showCheque(HttpServletRequest request, Model model, HttpSession session) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        HistoryEntity cheque = (HistoryEntity) session.getAttribute("cheque");
        model.addAttribute("chequeDate", dateFormat.format(cheque.getDate()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        model.addAttribute("cheque", cheque);
        model.addAttribute("account", session.getAttribute("account"));
        model.addAttribute("path", "/resources/imported_html/cheque.jsp");
        session.removeAttribute("cheque");
        session.removeAttribute("account");
        return "/index";
    }

    @RequestMapping("/paymentCancel")
    public String paymentCancel(HttpServletRequest request, HttpServletResponse response, Model model) {
        redirectHelper.RedirectToPage("/payments", request, response, model);
        return "/index";
    }

    @RequestMapping("/editPaymentConfirm")
    public String confirmChangesInPayment(@RequestParam(name = "payment_id", required = false, defaultValue = "0") int paymentId,
                                          @RequestParam(name = "payment_type", required = false, defaultValue = "") String paymentType,
                                          HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        paymentService.saveChangesToPayment(paymentId, paymentType);
        redirectHelper.RedirectToPage("/payments", request, response, model);
        return "/index";
    }

}