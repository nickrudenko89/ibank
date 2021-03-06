package Daos;

import Entities.ProfileEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ProfileDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public ProfileEntity getProfileById(int id) {
        String messageHql = "FROM ProfileEntity WHERE id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("id", id);
        return (ProfileEntity) query.uniqueResult();
    }
}
