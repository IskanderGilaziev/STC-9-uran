package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PerformanceDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Performance;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PerformanceDaoHibernate implements PerformanceDao {
    private static final Logger logger = Logger.getLogger(PerformanceDaoHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : lesson";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    private String logResult(boolean b) {
        return (b ? "Success" : "False") + " : ";
    }

    @Override
    public void addOrUpdateById(Performance performance) {
        logger.debug(DEBUG_BEFORE);
        if (performance != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(performance);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
    }

    @Override
    public Performance getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Session session = factory.openSession();
        Performance performance = (Performance) session.get(Performance.class, id);
        session.close();
        logger.info(logResult(performance != null));
        return performance;
    }

    @Override
    public void deleteById(long id) {
        logger.debug(DEBUG_BEFORE);
        if (id != 0) {
            Performance performance = getById(id);
            Session session = factory.openSession();
            session.beginTransaction();
            session.delete(performance);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
        logger.debug(DEBUC_AFTER);
    }

    @Override
    public List<Performance> getAll() {
        logger.debug(DEBUG_BEFORE);
        List<Performance> performanceList = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Performance");
            performanceList = query.list();
            session.close();
        }
        logger.info(logResult(!performanceList.isEmpty()) + performanceList.size());
        return performanceList;
    }

    @Override
    public List<Performance> getPerfomanceListByPerson(long personId) {
        logger.debug(DEBUG_BEFORE);
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Performance WHERE person_id = :personId");
            query.setParameter("personId", personId);
            List<Performance> performanceList = query.getResultList();
            for (Performance performance : performanceList)
                Hibernate.initialize(performance.getMark());
            session.close();
            logger.debug(DEBUC_AFTER);
            return performanceList;
        }
    }

    @Override
    public List<Performance> getByLessonId(long lessonId) {
        logger.debug(DEBUG_BEFORE);
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Performance WHERE lesson_id = :lessonId");
            query.setParameter("lessonId", lessonId);
            List<Performance> performanceList = query.getResultList();
            for (Performance performance : performanceList)
                Hibernate.initialize(performance.getMark());
            session.close();
            logger.debug(DEBUC_AFTER);
            return performanceList;
        }
    }

    @Override
    public List<Subject> getSubjectsForStudent(Person person) {
        logger.debug(DEBUG_BEFORE);
        List<Subject> subjectList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("SELECT distinct p.lesson.subject FROM Performance p WHERE p.person = :param");
            query.setParameter("param", person);
            subjectList = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.debug(subjectList.isEmpty() ? "fail" : "found " + subjectList.size());
        return subjectList;
    }

    /**
     * Список оценок студента по конкретному предмету
     *
     * @param person
     * @param subject
     * @return
     */
    @Override
    public List<Performance> getPerformanceForStudentBySubject(Person person, Subject subject) {
        logger.debug(DEBUG_BEFORE);
        List<Performance> performanceList = new ArrayList<>();
        if (subject != null) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                Query query = session.createQuery("FROM Performance p WHERE p.person = :persName AND p.lesson.subject = :subj ORDER BY p.lesson.date");
                query.setParameter("persName", person);
                query.setParameter("subj", subject);
                performanceList = query.getResultList();
                session.getTransaction().commit();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn("subject is a null object.");
        }
        logger.debug(performanceList.isEmpty() ? "fail" : "found " + performanceList.size());
        return performanceList;
    }
}
