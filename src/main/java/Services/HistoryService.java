package Services;

import Daos.HistoryDao;
import Entities.AccountEntity;
import Entities.HistoryEntity;
import Forms.MakePaymentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HistoryService {
    @Autowired
    private HistoryDao historyDao;
    @Autowired
    private PaymentService paymentService;

    public HistoryEntity savePaymentToHistory(AccountEntity account, MakePaymentForm makePaymentForm) {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setAccount(account);
        historyEntity.setPayment(paymentService.getPaymentById(makePaymentForm.getId()));
        historyEntity.setDate(new Date());
        historyEntity.setSum(makePaymentForm.getSum());
        historyDao.save(historyEntity);
        return historyEntity;
    }

    public List<HistoryEntity> showHistory(String account, Date startDate, Date endDate) {
        return historyDao.getHistory(account, startDate, endDate);
    }
}
