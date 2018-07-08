package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

@Repository
public class SpecialityDaoHibernate implements SpecialityDao {
    private static final Logger logger = Logger.getLogger(Speciality.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : speciality";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public Speciality getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Session session = factory.openSession();
        Speciality speciality = (Speciality) session.get(Speciality.class, id);
        session.close();
        logger.info(logResult(speciality != null));
        return speciality;
    }

    @Override
    public List<Speciality> getAllSpeciality() {
        logger.debug(DEBUG_BEFORE);
        List<Speciality> specialityList = null;
//        try (Session session = factory.openSession()) {
        try {
            Session session = factory.openSession();
            Query query = session.createQuery("FROM Speciality");
            specialityList = query.list();
            session.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info(logResult(!specialityList.isEmpty()) + specialityList.size());
        return specialityList;
    }

    @Override
    public boolean addOrUpdateSpeciality(Speciality speciality) {
        boolean result = false;
        logger.debug(DEBUG_BEFORE);
        if (speciality != null) {
            try (Session session = factory.openSession();) {
                session.beginTransaction();
                session.saveOrUpdate(speciality);
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

    /**
     * Добавить учебную дисциплину к программе
     *
     * @param specialityId
     * @param subject
     * @return
     */
    @Override
    public boolean addNewSubject(long specialityId, Subject subject) {
        boolean result = false;
        logger.debug(DEBUG_BEFORE);
        if (specialityId > 0) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                Speciality speciality = (Speciality) session.get(Speciality.class, specialityId);
                speciality.getSubjectSet().add(subject);
                session.saveOrUpdate(speciality);
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

    /**
     * Удалить данныек безвозвратно
     *
     * @param speciality
     * @return
     */
    @Override
    public boolean deleteSpecialityFull(Speciality speciality) {
        boolean result;
        logger.debug(DEBUG_BEFORE);
        if (speciality != null) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.delete(speciality);
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

    private String logResult(boolean b) {
        return (b ? "Success" : "False") + " : ";
    }

    private String logResult() {
        return "Unknown result of operation";
    }
}
