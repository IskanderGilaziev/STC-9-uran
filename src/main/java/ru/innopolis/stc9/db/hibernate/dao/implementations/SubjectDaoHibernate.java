package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

@Repository
public class SubjectDaoHibernate implements SubjectDao {
    private static final Logger logger = Logger.getLogger(Subject.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : subject";
    private static final String DEBUC_AFTER = "Before exit.";

    @Autowired
    private SessionFactory factory;

    @Override
    public Subject getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Session session = factory.openSession();
        Subject subject = (Subject) session.get(Subject.class, id);
        session.close();
        logger.info(logResult(subject != null));
        return subject;
    }

    @Override
    public List<Subject> getAllSubjects() {
        logger.debug(DEBUG_BEFORE);
        List<Subject> subjectList;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Subject");
            subjectList = query.list();
            session.close();
        }
        logger.info((subjectList == null) ? "bad qwery" : "found " + subjectList.size() + " subject(s)");
        return subjectList;
    }

    @Override
    public boolean addOrUpdateSubject(Subject subject) {
        boolean result = false;
        logger.debug(DEBUG_BEFORE);
        if (subject != null) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.saveOrUpdate(subject);
                session.getTransaction().commit();
                session.close();
                result = true;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn(WARN_NPE);
        }
        return result;
    }

    @Override
    public boolean deleteSubjectFull(Subject subject) {
        boolean result;
        logger.debug(DEBUG_BEFORE);
        if (subject != null) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.delete(subject);
                session.getTransaction().commit();
                session.close();
                result = true;
            } catch (Exception e) {
                result = false;
            }
            logger.info(logResult());
        } else {
            logger.warn(WARN_NPE);
            result = false;
        }
        logger.debug(DEBUC_AFTER);
        return result;
    }


    @Override
    public Subject getByName(String name) {
        Subject subject = null;
        if (name != null && !name.isEmpty()) {
            try (Session session = factory.openSession()) {
                Query query = session.createQuery("FROM Subject WHERE name = :param");
                query.setParameter("param", name);
                if (query.list() != null && !query.list().isEmpty()) {
                    subject = (Subject) query.list().get(0);
                }
            }
        }
        return subject;
    }

    @Override
    public void toDetached(Subject subject) {
        logger.debug(DEBUG_BEFORE);
        if (subject != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.evict(subject);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
    }

    private String logResult(boolean b) {
        return (b ? "Success" : "False") + " : ";
    }

    private String logResult() {
        return "Unknown result of operation";
    }

}
