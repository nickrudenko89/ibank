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
    private SessionFactory sessionFactory;

    public UserEntity getUserById(int id) {
        String messageHql = "FROM UserEntity WHERE id = :id";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("id", id);
        return (UserEntity) query.uniqueResult();
    }

    public UserEntity getUserByLoginAndPassword(String login, String password) {
        String messageHql = "FROM UserEntity WHERE login = :login AND password= :password";
        Query query = sessionFactory.getCurrentSession().createQuery(messageHql);
        query.setParameter("login", login);
        query.setParameter("password", password);
        return (UserEntity) query.uniqueResult();
    }

    public void update(UserEntity user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
