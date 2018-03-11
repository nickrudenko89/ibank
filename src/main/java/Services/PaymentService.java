package Services;

import Utils.Constants;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public String getPaymentErrorById(String id) {
        if (Constants.Strings.ONE.equals(id))
            return Constants.Errors.PAYMENT_SUM_ERROR;
        else if (Constants.Strings.TWO.equals(id))
            return Constants.Errors.NOT_ENOUGH_MONEY_ERROR;
        else if (Constants.Strings.THREE.equals(id))
            return Constants.Errors.INCORRECT_ACCOUNT_ERROR;
        else
            return "";
    }
}
