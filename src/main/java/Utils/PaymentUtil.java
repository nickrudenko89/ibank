package Utils;

import Entities.PaymentEntity;
import Forms.MakePaymentForm;

public class PaymentUtil {
    public static MakePaymentForm setPaymentToForm(PaymentEntity payment) {
        MakePaymentForm makePaymentForm = new MakePaymentForm();
        makePaymentForm.setId(payment.getId());
        makePaymentForm.setType(payment.getType());
        return makePaymentForm;
    }
}
