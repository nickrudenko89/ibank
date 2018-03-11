package Controllers;

import Daos.AccountDao;
import Daos.UserDao;
import Entities.AccountEntity;
import Entities.UserEntity;
import Services.AccountService;
import Utils.Constants;
import Utils.CookieUtil;
import Utils.RedirectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Controller
public class AccountsController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountService accountService;

    @RequestMapping("/accounts")
    public String showAccounts(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
            model.addAttribute("clientId", Constants.UserType.BANK_CLIENT);
            model.addAttribute("employeeId", Constants.UserType.BANK_EMPLOYEE);
            model.addAttribute("userType", user.getUserType());
            model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
            switch (user.getUserType()) {
                case 1:
                    model.addAttribute("accountsToOpen", accountDao.getAccountsByStatus(Constants.BankAccount.ACCOUNT_TO_OPEN));
                    model.addAttribute("accountsToClose", accountDao.getAccountsByStatus(Constants.BankAccount.ACCOUNT_TO_CLOSE));
                    break;
                case 2:
                    model.addAttribute("accounts", user.getAccounts());
                    model.addAttribute("openedAccountStatus", Constants.BankAccount.OPENED_ACCOUNT);
                    break;
            }
            model.addAttribute("path", "/resources/imported_html/accounts.jsp");
        }
        return "/index";
    }

    @RequestMapping("/openAccount")
    public String openAccount(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
            model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
            model.addAttribute("currencies", Constants.BankAccount.BANK_CURRENCIES);
            model.addAttribute("action", "open");
            model.addAttribute("path", "/resources/imported_html/user_account_changed.jsp");
        }
        return "/index";
    }

    @RequestMapping("/closeAccount")
    public String closeAccount(@RequestParam(name = "id", required = false, defaultValue = "0") int accountId,
                               HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = CookieUtil.getBankCookie(request, response);
        if (cookie != null) {
            AccountEntity account = accountDao.getAccountById(accountId);
            if (account != null) {
                UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
                model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
                if (user.getId() == account.getUser().getId()) {
                    if (account.getBalance() != 0) {
                        model.addAttribute("message", Constants.Errors.ACCOUNT_BALANCE_ERROR);
                    } else {
                        account.setStatus(Constants.BankAccount.ACCOUNT_TO_CLOSE);
                        accountDao.updateAccount(account);
                        model.addAttribute("message", Constants.Messages.ACCOUNT_MOVED_TO_DELETED);
                    }
                    model.addAttribute("action", "close");
                    model.addAttribute("path", "/resources/imported_html/user_account_changed.jsp");
                    return "/index";
                }
            }
            RedirectUtil.RedirectToPage("/accounts", userDao, request, response, model);
        }
        return "/index";
    }

    @RequestMapping("/createNewAccount")
    public String createNewAccount(@RequestParam(name = "currency", required = false, defaultValue = "") String accountCurrency,
                                   HttpServletRequest request, HttpServletResponse response, Model model) {
        if (accountCurrency != null) {
            if (!Constants.Strings.EMPTY_STRING.equals(accountCurrency)) {
                Cookie cookie = CookieUtil.getBankCookie(request, response);
                if (cookie != null) {
                    UserEntity user = userDao.getUserById(Integer.valueOf(cookie.getValue()));
                    model.addAttribute("userName", user.getProfile().getLastName() + " " + user.getProfile().getFirstName());
                    String accountNumber = accountCurrency;
                    do {
                        for (int counter = 1; counter <= Constants.BankAccount.ACCOUNT_NUMBER_LENGTH; counter++) {
                            Random randomNumber = new Random();
                            accountNumber += String.valueOf(randomNumber.nextInt(10));
                        }
                    } while (accountDao.getAccountByAccountNumber(accountNumber) != null);
                    AccountEntity account = new AccountEntity();
                    account.setAccountNumber(accountNumber);
                    account.setCurrency(accountCurrency);
                    account.setBalance(0);
                    account.setStatus(Constants.BankAccount.ACCOUNT_TO_OPEN);
                    account.setUser(user);
                    accountDao.insertAccount(account);
                    model.addAttribute("message", Constants.Messages.ACCOUNT_CREATED);
                    model.addAttribute("action", "close");
                    model.addAttribute("path", "/resources/imported_html/user_account_changed.jsp");
                    return "/index";
                }
            }
        }
        RedirectUtil.RedirectToPage("/accounts", userDao, request, response, model);
        return "/index";
    }

    @RequestMapping("/accountsQuery")
    public String applyToOpenAccount(@RequestParam(name = "action", required = false, defaultValue = "") String accountAction,
                                     @RequestParam(name = "id", required = false, defaultValue = "0") int accountId,
                                     HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!Constants.Strings.EMPTY_STRING.equals(accountAction)) {
            if ("apply_to_open".equals(accountAction))
                accountService.moveAccountToOpened(userDao, accountDao, accountId, Constants.BankAccount.ACCOUNT_TO_OPEN, request, response, model);
            else if ("refuse_to_close".equals(accountAction))
                accountService.moveAccountToOpened(userDao, accountDao, accountId, Constants.BankAccount.ACCOUNT_TO_CLOSE, request, response, model);
            else if ("refuse_to_open".equals(accountAction))
                accountService.deleteAccount(userDao, accountDao, accountId, Constants.BankAccount.ACCOUNT_TO_OPEN, request, response, model);
            else if ("apply_to_close".equals(accountAction))
                accountService.deleteAccount(userDao, accountDao, accountId, Constants.BankAccount.ACCOUNT_TO_CLOSE, request, response, model);
        }
        return "/index";
    }

}
