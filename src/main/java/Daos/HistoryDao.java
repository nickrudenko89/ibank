package Daos;

import Entities.HistoryEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class HistoryDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public void save(HistoryEntity historyEntity) {
        sessionFactory.getCurrentSession().save(historyEntity);
    }

    public List<HistoryEntity> getHistory(String account, Date startDate, Date endDate) {
        String messageHql = "FROM HistoryEntity WHERE account.accountNumber = :account AND date >= :startDate AND date<= :endDate";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("account", account);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.list();
    }
}
