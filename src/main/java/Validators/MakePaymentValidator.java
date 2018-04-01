package Validators;

import Entities.AccountEntity;
import Forms.MakePaymentForm;
import Services.AccountService;
import Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MakePaymentValidator implements Validator {
    @Autowired
    private AccountService accountService;

    public boolean supports(Class<?> aClass) {
        return MakePaymentForm.class.isAssignableFrom(aClass);
    }

    public void validate(Object o, Errors errors) {
        MakePaymentForm makePaymentForm = (MakePaymentForm) o;
        if (makePaymentForm.getSum() <= 0) {
            errors.rejectValue("sum", "sum.zero", Constants.Errors.PAYMENT_SUM_ZERO_ERROR);
        }
        String accountNumber = makePaymentForm.getAccount();
        if (accountNumber == null) {
            errors.rejectValue("account", "account.null", Constants.Errors.INCORRECT_ACCOUNT_ERROR);
        } else {
            AccountEntity account = accountService.getUserAccountByAccountNumber(accountNumber);
            if ((account.getBalance() - makePaymentForm.getSum()) < 0) {
                errors.rejectValue("account", "account.noMoney", Constants.Errors.NOT_ENOUGH_MONEY_ERROR);
            }
        }
    }
}
