package Services;

import Daos.AccountDao;
import Daos.UserDao;
import Entities.AccountEntity;
import Utils.Constants;
import Utils.RedirectUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AccountService {
    public void moveAccountToOpened(UserDao userDao, AccountDao accountDao, int accountId, int accountStatus, HttpServletRequest request, HttpServletResponse response, Model model) {
        AccountEntity account = accountDao.getAccountById(accountId);
        if (account != null) {
            if (account.getStatus() == accountStatus) {
                account.setStatus(Constants.BankAccount.OPENED_ACCOUNT);
                accountDao.updateAccount(account);
            }
        }
        RedirectUtil.RedirectToPage("/accounts", userDao, request, response, model);
    }

    public void deleteAccount(UserDao userDao, AccountDao accountDao, int accountId, int accountStatus, HttpServletRequest request, HttpServletResponse response, Model model) {
        AccountEntity account = accountDao.getAccountById(accountId);
        if (account != null) {
            if (account.getStatus() == accountStatus) {
                accountDao.deleteAccount(account);
            }
        }
        RedirectUtil.RedirectToPage("/accounts", userDao, request, response, model);
    }
}
