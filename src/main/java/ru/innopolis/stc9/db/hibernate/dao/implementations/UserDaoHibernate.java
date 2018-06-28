package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.UserDao;
import ru.innopolis.stc9.pojo.hibernate.entities.User;

import java.util.List;

@Repository
public class UserDaoHibernate implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public User getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Session session = factory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        logger.info(logResult(user != null));
        return user;
    }

    @Override
    public User getByLogin(String name) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        logger.debug(DEBUG_BEFORE);
        List<User> userList = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("from User");
            userList = query.list();
        }
        logger.info(logResult(!userList.isEmpty()) + userList.size());
        return userList;
    }

    @Override
    public void addUser(User user) {
        logger.debug(DEBUG_BEFORE);
        Long id = -1L;
        if (user != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE + "user");
        }
//        logger.info(logResult(id > 0) + user.getLogin());
        int u = 0;
    }

    @Override
    public void addUserEvict(User user) {
        logger.debug(DEBUG_BEFORE);
        Long id = -1L;
        if (user != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(user);
            session.evict(user);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE + "user");
        }
//        logger.info(logResult(id > 0) + user.getLogin());
        int u = 0;
    }

    @Override
    public void updateUserData(User user) {
        logger.debug(DEBUG_BEFORE);
        if (user != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
            logger.info(logResult());
        } else {
            logger.warn(WARN_NPE + "user");
        }
        logger.debug(DEBUC_AFTER);
    }

    @Override
    public void deleteByUserId(User user) {
        logger.debug(DEBUG_BEFORE);
        if (user != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            logger.info(logResult());
        } else {
            logger.warn(WARN_NPE + "user");
        }
        logger.debug(DEBUC_AFTER);
    }

    private String logResult(boolean b) {
        return (b ? "Success" : "False") + " : ";
    }

    private String logResult() {
        return "Unknown result of operation";
    }
}
