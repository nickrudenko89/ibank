package Services;

import Daos.PaymentDao;
import Entities.PaymentEntity;
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

    public PaymentEntity getPaymentById(int paymentId) {
        return paymentDao.getPaymentById(paymentId);
    }

    public void saveChangesToPayment(int paymentId, String paymentType) {
        PaymentEntity payment = getPaymentById(paymentId);
        payment.setType(paymentType);
        paymentDao.update(payment);
    }

}
