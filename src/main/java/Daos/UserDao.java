package Daos;

import Entities.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDao {
    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public UserEntity getUserbyLoginAndPassword(String login, String password) {
        String messageHql = "FROM UserEntity WHERE login = :login AND password= :password";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("login", login);
        query.setParameter("password", password);
        return (UserEntity) query.uniqueResult();
    }
}
