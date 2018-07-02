package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubjectDaoHibernate implements SubjectDao {
    private static final Logger logger = Logger.getLogger(SubjectDaoHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public List<Subject> getAllSubjects() {
        logger.debug(DEBUG_BEFORE);
        List<Subject> subjectList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Subject");
            subjectList = query.list();
            session.close();
        }
        logger.info(subjectList.size());
        return subjectList;
    }

    @Override
    public Subject getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Subject subject = null;
        try (Session session = factory.openSession()) {
            subject = (Subject) session.get(Subject.class, id);
            session.close();
        }
        return subject;
    }

    @Override
    public boolean saveOrUpdate(Subject subject) {
        boolean result = false;
        logger.debug(DEBUG_BEFORE);
        if (subject != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(subject);
            session.getTransaction().commit();
            session.close();
            result = true;
        } else {
            logger.warn(WARN_NPE);
        }
        return result;
    }

    @Override
    public long countOfTeachersBySubject(Subject subject) {
        logger.debug(DEBUG_BEFORE);
        long result = -1;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Subject s WHERE s.teacher.subject = :param");
            query.setParameter("param", subject.getName());
            List r = query.list();
            if (r != null) {
                result = (Long) r.get(0);
            }
        } catch (Exception e) {
            int u = 0;
        }
        return result;
    }
}
