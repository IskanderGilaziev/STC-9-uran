package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeamDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;

import java.util.List;

@Repository
public class TeamDaoHibernate implements TeamDao {
    private static final Logger logger = Logger.getLogger(Person.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : team";

    @Autowired
    private SessionFactory factory;

    /**
     * Найти группу по ее id
     *
     * @param id
     * @return
     */
    @Override
    public Team getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Team team = null;
        try (Session session = factory.openSession()) {
            team = (Team) session.get(Team.class, id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info(team != null ? "Success " + team : "Fail");
        return team;
    }

    /**
     * Освежить данные в базе
     *
     * @param team
     */
    @Override
    public void addOrUpdate(Team team) {
        logger.debug(DEBUG_BEFORE);
        if (team != null) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.saveOrUpdate(team);
                session.getTransaction().commit();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            logger.warn(WARN_NPE);
        }
    }

    /**
     * Список всех групп, присутствующих в системе
     *
     * @return
     */
    @Override
    public List<Team> getAll() {
        logger.debug(DEBUG_BEFORE);
        List<Team> teamList = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Team");
            teamList = query.list();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info(teamList != null ? "Found " + teamList.size() + " group(-s)" : "Fail");
        return teamList;
    }

    /**
     * Выборка из БД тех групп, которые еще не окончили обучение
     * (т.е. год начала обучения + срок обучения не превышают текущий год.
     *
     * @param earliestYear
     * @return
     */
    @Override
    public List<Team> getAllSuitForSpecialty(int earliestYear) {
        logger.debug(DEBUG_BEFORE);
        List<Team> teamList = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Team t WHERE t.yStart >= " + earliestYear);
            teamList = query.list();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info(teamList != null ? "Found " + teamList.size() + " group(-s)" : "Fail");
        return teamList;
    }
}
