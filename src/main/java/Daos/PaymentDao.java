package Daos;

import Entities.PaymentEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PaymentDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public PaymentEntity getPaymentById(int id) {
        String messageHql = "FROM PaymentEntity WHERE id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("id", id);
        return (PaymentEntity) query.uniqueResult();
    }

    public List<PaymentEntity> getAllPayments() {
        String messageHql = "FROM PaymentEntity";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        return query.list();
    }

    public void update(PaymentEntity payment) {
        sessionFactory.getCurrentSession().update(payment);
    }

}
