package ru.innopolis.stc9.db.hibernate.dao.implementations;


import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.LessonDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LessonDaoHibernate implements LessonDao {
    private static final Logger logger = Logger.getLogger(Lesson.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : lesson";
    private static final String DEBUC_AFTER = "Before exit.";

    private String logResult(boolean b) {
        return (b ? "Success" : "False") + " : ";
    }

    @Autowired
    private SessionFactory factory;

    @Override
    public void addOrUpdateById(Lesson lesson) {
        logger.debug(DEBUG_BEFORE);
        if (lesson != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(lesson);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
    }

    @Override
    public Lesson getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Session session = factory.openSession();
        Lesson lesson = (Lesson) session.get(Lesson.class, id);
        session.close();
        logger.info(logResult(lesson != null));
        return lesson;
    }

    @Override
    public List<Lesson> getAll() {
        logger.debug(DEBUG_BEFORE);
        List<Lesson> lessonList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Lesson");
            lessonList = query.list();
            session.close();
        }
        logger.info(logResult(!lessonList.isEmpty()) + lessonList.size());
        return lessonList;
    }


    @Override
    public void deleteById(long id) {
        logger.debug(DEBUG_BEFORE);
        if (id != 0) {
            Lesson lesson = getById(id);
            Session session = factory.openSession();
            session.beginTransaction();
            session.delete(lesson);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
        logger.debug(DEBUC_AFTER);
    }

    @Override
    public List<Lesson> getLessonListBySubjId(long subjectId) {
        logger.debug(DEBUG_BEFORE);
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Lesson l WHERE l.subject.id = :subjectId");
            query.setParameter("subjectId", subjectId);
            List<Lesson> lessons = query.getResultList();
            for (Lesson lesson : lessons)
                Hibernate.initialize(lesson.getSubject());
            session.close();
            logger.debug(DEBUC_AFTER);
            return lessons;
        }
    }
}