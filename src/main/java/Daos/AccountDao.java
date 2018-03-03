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
    public SessionFactory sessionFactory;

    public List<AccountEntity> getAccountsByUserId(int id) {
        String messageHql = "FROM ProfileEntity WHERE id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("id", id);
        return (List<AccountEntity>) query.getResultList();
    }

}
