package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeamDao;
import ru.innopolis.stc9.pojo.hibernate.entities.*;

import java.util.*;

@Repository
public class TeamDaoHibernate implements TeamDao {
    private static final Logger logger = Logger.getLogger(SubjectDaoHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public Team getById(long id) {
        Team result = null;
        if (id > 0) {
            Session session = factory.openSession();
            result = (Team) session.get(Team.class, id);
            session.close();
        }
        return result;
    }

    @Override
    public Team getByIdWithStudentsAndSubjects(long id) {
        Team result = null;
        if (id > 0) {
            Session session = factory.openSession();
            result = (Team) session.get(Team.class, id);
            for (Student s : result.getStudentSet()) {
                s.getId();
                s.getPerson().getId();
                s.getPerson().getName();
            }
            for (Subject s : result.getSubjectSet()) {
                s.getId();
                s.getName();
                for (Teacher t : s.getTeacherSet()) {
                    t.getId();
                    t.getPerson().getId();
                    t.getPerson().getName();
                }
            }
            session.close();
        }
        return result;
    }

    @Override
    public List<Team> getAll() {
        logger.debug(DEBUG_BEFORE);
        List<Team> teamList = new ArrayList<>();
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Team");
            teamList = query.list();
            session.close();
        }
        logger.info(teamList.size());
        return teamList;
    }

    @Override
    public long getTeamCount() {
        logger.debug(DEBUG_BEFORE);
        long result = -1;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("SELECT COUNT (t.id ) FROM Team t");
//            query.setParameter("param", securityRole);
            List r = query.list();
            if (r != null) {
                result = (Long) r.get(0);
            }
        }
        return result;
    }

    @Override
    public boolean saveOrUpdate(Team team) {
        logger.debug(DEBUG_BEFORE);
        boolean result = false;
        if (team != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(team);
            session.getTransaction().commit();
            session.close();
            result = true;
        } else {
            logger.warn(WARN_NPE);
        }
        return result;
    }

    @Override
    public Set<Student> getAllStudentInTeam(Team team) {
        logger.debug(DEBUG_BEFORE);
        Set<Student> studentSet = new HashSet<>();

        try (Session session = factory.openSession()) {
            Query query = session.createQuery("SELECT t.studentSet FROM Team t WHERE t.id = :param");
            query.setParameter("param", team.getId());
            List r = query.list();
            studentSet.addAll(r);
        }
        return studentSet;
    }

    @Override
    public boolean removeStudentFromTeam(long id, long pers) {
        boolean b = false;
        Team result = null;
        Person remove = null;
        if (id > 0) {
            Session session = factory.openSession();
            result = (Team) session.get(Team.class, id);
            for (Student s : result.getStudentSet()) {
                s.getId();
                if (s.getPerson().getId() == pers) {
                }
                s.getPerson().getName();
            }
            Iterator<Student> iterator = result.getStudentSet().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getPerson().getId() == pers) {
                    iterator.remove();
                }
            }
            session.beginTransaction();
            session.merge(result);
            session.getTransaction().commit();
            b = true;
            session.close();
        }
        return b;
    }
}
