package Daos;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Repository
@Transactional
public class HistoryDao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
}
