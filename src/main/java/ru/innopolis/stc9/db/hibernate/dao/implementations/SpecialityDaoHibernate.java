package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;

import java.util.List;
import java.util.Set;

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
        Speciality speciality = null;
        try (Session session = factory.openSession()) {
            speciality = (Speciality) session.get(Speciality.class, id);
        } catch (Exception e) {
            logger.error("id = " + id + "; message: " + e.getMessage());
        }
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
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info(specialityList != null ? "found " + specialityList.size() + " objects" : "Fail");
        return specialityList;
    }

    @Override
    public void addOrUpdateSpeciality(Speciality speciality) {
        logger.debug(DEBUG_BEFORE);
        if (speciality != null) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.saveOrUpdate(speciality);
                session.getTransaction().commit();
            } catch (Exception e) {
                logger.error("argument = " + speciality + "; message: " + e.getMessage());
            }
        } else {
            logger.warn(WARN_NPE);
        }
    }

    /**
     * Удаляет только специальность, оставляя группы в системе
     *
     * @param id
     */
    @Override
    public void deleteBySpecialityIdFull(long id) {
        logger.debug(DEBUG_BEFORE);
        if (id > 0) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                Speciality speciality = (Speciality) session.get(Speciality.class, id);
                Set<Team> teamSet = speciality.getTeamSet();
                for (Team t : teamSet) {
                    t.setSpeciality(null);
                }
                session.saveOrUpdate(speciality);
                session.delete(speciality);
                session.getTransaction().commit();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn(WARN_NPE);
        }
        logger.debug(DEBUC_AFTER);
    }

    private String logResult(boolean b) {
        return (b ? "Success" : "False") + " : ";
    }
}
