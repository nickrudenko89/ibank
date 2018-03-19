package Services;

import Daos.PaymentDao;
import Entities.PaymentEntity;
import Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public List<PaymentEntity> getAllPayments() {
        return paymentDao.getAll();
    }

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
