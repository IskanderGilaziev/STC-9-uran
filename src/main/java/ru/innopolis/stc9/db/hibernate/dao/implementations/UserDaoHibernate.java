package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.UserDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Performance;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.hibernate.entities.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoHibernate implements UserDao {
    private static final Logger logger = Logger.getLogger(UserDaoHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : user";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public User getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Session session = factory.openSession();
        User user = session.get(User.class, id);
        session.close();
        logger.info(logResult(user != null));
        return user;
    }

    @Override
    public User getByLogin(String name) {
        logger.debug(DEBUG_BEFORE);
        User result = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User WHERE login = :param");
            query.setParameter("param", name);
            List<User> userList = query.list();
            if (userList.size() == 1) {
                result = userList.get(0);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info(result == null ? "Fail" : "User with login " + name + " was found.");
        return result;
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
        if (user != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
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
            logger.warn(WARN_NPE);
        }
        logger.info(logResult(id > 0));
    }

    @Override
    public void updateUserData(User user) {
        logger.debug(DEBUG_BEFORE);
        if (user != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            session.close();
            logger.info(logResult());
        } else {
            logger.warn(WARN_NPE);
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
            logger.warn(WARN_NPE);
        }
        logger.debug(DEBUC_AFTER);
    }

    public long userCountWithRole(String securityRole) {
        logger.debug(DEBUG_BEFORE);
        long result = -1;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("SELECT COUNT (u.role ) FROM User u WHERE u.role = :param");
            query.setParameter("param", securityRole);
            List r = query.list();
            if (r != null) {
                result = (Long) r.get(0);
            }
        }
        logger.info(logResult(result >= 0) + result);
        return result;
    }

    @Override
    public Set<Subject> allSubjectsForStudent(String login) {
        logger.debug(DEBUG_BEFORE);
        Set<Subject> subjectSet = new HashSet<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Performance p WHERE p.person.user.login = :param");
            query.setParameter("param", login);
            List<Performance> performance = query.list();
            for (Performance p : performance) {
                subjectSet.add(p.getLesson().getSubject());
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info(!subjectSet.isEmpty() ? "Fail" : "User with login " + login + " was found.");
        return subjectSet;
    }

    private String logResult(boolean b) {
        return (b ? "Success" : "False") + " : ";
    }

    private String logResult() {
        return "Unknown result of operation";
    }
}
