package Services;

import Daos.AccountDao;
import Entities.AccountEntity;
import Entities.UserEntity;
import Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public List<AccountEntity> getClientAccountsByStatus(int status) {
        return accountDao.getAccountsByStatus(status);
    }

    public AccountEntity getUserAccountById(int id) {
        return accountDao.getAccountById(id);
    }

    public AccountEntity getUserAccountByAccountNumber(String accountNumber) {
        return accountDao.getAccountByAccountNumber(accountNumber);
    }

    public void makePayment(AccountEntity account, float paymentSum) {
        account.setBalance(account.getBalance() - paymentSum);
        accountDao.update(account);
    }

    public boolean closeAccount(int id) {
        AccountEntity account = getUserAccountById(id);
        if (account.getBalance() != 0) {
            return false;
        } else {
            account.setStatus(Constants.BankAccount.ACCOUNT_TO_CLOSE);
            accountDao.update(account);
            return true;
        }
    }

    public void openAccount(UserEntity owner, String accountCurrency) {
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
        account.setUser(owner);
        accountDao.save(account);
    }

    public void moveAccountToOpened(int accountId, int accountStatus) {
        AccountEntity account = accountDao.getAccountById(accountId);
        if (account.getStatus() == accountStatus) {
            account.setStatus(Constants.BankAccount.OPENED_ACCOUNT);
            accountDao.update(account);
        }
    }

    public void deleteAccount(int accountId, int accountStatus) {
        AccountEntity account = accountDao.getAccountById(accountId);
        if (account.getStatus() == accountStatus) {
            accountDao.delete(account);
        }
    }
}
