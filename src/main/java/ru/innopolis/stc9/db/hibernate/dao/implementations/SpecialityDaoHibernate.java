package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

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
    public List<Speciality> getAllSpecialitys() {
        logger.debug(DEBUG_BEFORE);
        List<Speciality> specialityList = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Speciality");
            specialityList = query.list();
            session.close();
        }
        logger.info(logResult(!specialityList.isEmpty()) + specialityList.size());
        return specialityList;
    }

    @Override
    public void addOrUpdateSpeciality(Speciality speciality) {
        logger.debug(DEBUG_BEFORE);
        if (speciality != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(speciality);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
    }

    @Override
    public void deleteBySpecialityId(long id) {
        logger.debug(DEBUG_BEFORE);
        if (id != 0) {
            Speciality speciality = getById(id);
            Session session = factory.openSession();
            session.beginTransaction();
            session.delete(speciality);
            session.getTransaction().commit();
            session.close();
            logger.info(logResult());
        } else {
            logger.warn(WARN_NPE);
        }
        logger.debug(DEBUC_AFTER);
    }


    @Override
    public Speciality getByName(String name) {
        Speciality speciality = null;
        if (name != null && !name.isEmpty()) {
            try (Session session = factory.openSession()) {
                Query query = session.createQuery("FROM Speciality WHERE name = :param");
                query.setParameter("param", name);
                if (query.list() != null && !query.list().isEmpty()) {
                    speciality = (Speciality) query.list().get(0);
                }
            }
        }
        return speciality;
    }

    @Override
    public void toDetached(Speciality speciality) {
        logger.debug(DEBUG_BEFORE);
        if (speciality != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.evict(speciality);
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
