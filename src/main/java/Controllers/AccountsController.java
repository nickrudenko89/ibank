package Controllers;

import Entities.UserEntity;
import Enums.UserTypeEnum;
import Services.AccountService;
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

@Controller
public class AccountsController {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RedirectHelper redirectHelper;

    @RequestMapping("/accounts")
    public String showAccounts(HttpServletRequest request, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("clientId", UserTypeEnum.CLIENT.getIndex());
        model.addAttribute("employeeId", UserTypeEnum.EMPLOYEE.getIndex());
        model.addAttribute("userType", user.getUserType());
        model.addAttribute("userName", UserUtil.getUserName(user));
        switch (user.getUserType()) {
            case 1:
                model.addAttribute("accountsToOpen", accountService.getClientAccountsByStatus(Constants.BankAccount.ACCOUNT_TO_OPEN));
                model.addAttribute("accountsToClose", accountService.getClientAccountsByStatus(Constants.BankAccount.ACCOUNT_TO_CLOSE));
                break;
            case 2:
                model.addAttribute("accounts", user.getAccounts());
                model.addAttribute("openedAccountStatus", Constants.BankAccount.OPENED_ACCOUNT);
                break;
        }
        model.addAttribute("path", "/resources/imported_html/accounts.jsp");
        return "/index";
    }

    @RequestMapping("/openAccount")
    public String openAccount(HttpServletRequest request, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        model.addAttribute("currencies", Constants.BankAccount.BANK_CURRENCIES);
        model.addAttribute("action", "open");
        model.addAttribute("path", "/resources/imported_html/user_account_changed.jsp");
        return "/index";
    }

    @RequestMapping("/closeAccount")
    public String closeAccount(@RequestParam(name = "id", required = false, defaultValue = "0") int accountId,
                               HttpServletRequest request, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        if (accountService.closeAccount(accountId)) {
            model.addAttribute("message", Constants.Messages.ACCOUNT_MOVED_TO_DELETED);
        } else {
            model.addAttribute("message", Constants.Errors.ACCOUNT_BALANCE_ERROR);
        }
        model.addAttribute("action", "close");
        model.addAttribute("path", "/resources/imported_html/user_account_changed.jsp");
        return "/index";
    }

    @RequestMapping("/openAccountProcess")
    public String openAccountProcess(@RequestParam(name = "currency", required = false, defaultValue = "") String accountCurrency,
                                     HttpServletRequest request, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request);
        UserEntity user = userService.getLoggedUser(Integer.valueOf(cookie.getValue()));
        model.addAttribute("userName", UserUtil.getUserName(user));
        accountService.openAccount(user, accountCurrency);
        model.addAttribute("message", Constants.Messages.ACCOUNT_CREATED);
        model.addAttribute("action", "close");
        model.addAttribute("path", "/resources/imported_html/user_account_changed.jsp");
        return "/index";
    }

    @RequestMapping("/accountOperationQuery")
    public String accountOperationQuery(@RequestParam(name = "action", required = false, defaultValue = "") String accountAction,
                                        @RequestParam(name = "id", required = false, defaultValue = "0") int accountId,
                                        HttpServletRequest request, HttpServletResponse response, Model model) {
        if ("apply_to_open".equals(accountAction))
            accountService.moveAccountToOpened(accountId, Constants.BankAccount.ACCOUNT_TO_OPEN);
        else if ("refuse_to_close".equals(accountAction))
            accountService.moveAccountToOpened(accountId, Constants.BankAccount.ACCOUNT_TO_CLOSE);
        else if ("refuse_to_open".equals(accountAction))
            accountService.deleteAccount(accountId, Constants.BankAccount.ACCOUNT_TO_OPEN);
        else if ("apply_to_close".equals(accountAction))
            accountService.deleteAccount(accountId, Constants.BankAccount.ACCOUNT_TO_CLOSE);
        redirectHelper.RedirectToPage("/accounts", request, response, model);
        return "/index";
    }

}
