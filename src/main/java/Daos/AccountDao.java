package Daos;

import Entities.AccountEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccountDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public AccountEntity getAccountById(int id) {
        String messageHql = "FROM AccountEntity WHERE id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("id", id);
        return (AccountEntity) query.uniqueResult();
    }

    public AccountEntity getAccountByAccountNumber(String accountNumber) {
        String messageHql = "FROM AccountEntity WHERE accountNumber = :accountNumber";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("accountNumber", accountNumber);
        return (AccountEntity) query.uniqueResult();
    }

    public List<AccountEntity> getAccountsByStatus(int status) {
        String messageHql = "FROM AccountEntity WHERE status = :status";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("status", status);
        return query.list();
    }

    public void update(AccountEntity account) {
        sessionFactory.getCurrentSession().update(account);
    }

    public void save(AccountEntity account) {
        sessionFactory.getCurrentSession().save(account);
    }

    public void delete(AccountEntity account) {
        sessionFactory.getCurrentSession().delete(account);
    }
}
