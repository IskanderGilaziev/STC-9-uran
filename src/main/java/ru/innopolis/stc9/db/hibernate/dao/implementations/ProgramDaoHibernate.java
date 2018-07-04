package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.ProgramDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Program;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

import java.util.List;

public class ProgramDaoHibernate implements ProgramDao {
    private static final Logger logger = Logger.getLogger(Program.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null object : program";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public Program getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Session session = factory.openSession();
        Program program = (Program) session.get(Program.class, id);
        session.close();
        logger.info(logResult(program != null));
        return program;
    }

    @Override
    public List<Program> getAllPrograms() {
        logger.debug(DEBUG_BEFORE);
        List<Program> programList = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Program");
            programList = query.list();
            session.close();
        }
        logger.info(logResult(!programList.isEmpty()) + programList.size());
        return programList;
    }

    @Override
    public void addOrUpdateProgram(Program program) {
        logger.debug(DEBUG_BEFORE);
        if (program != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(program);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
    }

    @Override
    public void deleteByProgramId(long id) {
        logger.debug(DEBUG_BEFORE);
        if (id != 0) {
            Program program = getById(id);
            Session session = factory.openSession();
            session.beginTransaction();
            session.delete(program);
            session.getTransaction().commit();
            session.close();
            logger.info(logResult());
        } else {
            logger.warn(WARN_NPE);
        }
        logger.debug(DEBUC_AFTER);
    }

    @Override
    public List<Program> getProgramByRole(int role) {
        logger.debug(DEBUG_BEFORE);
        List<Program> programList;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Program where status = :role");
            query.setParameter("param", Status.values()[role]);
            programList = query.list();
        }
        logger.info(logResult(!programList.isEmpty()) + programList.size());
        return programList;
    }

    @Override
    public List<Program> getProgramByRoleAndNullUser(Status status) {
        logger.debug(DEBUG_BEFORE);
        List<Program> programList;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Program where status = :role");
            query.setParameter("role", status);
            programList = query.list();
        }
        logger.info(logResult(!programList.isEmpty()) + programList.size());
        return programList;
    }

    @Override
    public Program getByName(String name) {
        Program program = null;
        if (name != null && !name.isEmpty()) {
            try (Session session = factory.openSession()) {
                Query query = session.createQuery("FROM Program WHERE name = :param");
                query.setParameter("param", name);
                if (query.list() != null && !query.list().isEmpty()) {
                    program = (Program) query.list().get(0);
                }
            }
        }
        return program;
    }

    @Override
    public void toDetached(Program program) {
        logger.debug(DEBUG_BEFORE);
        if (program != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.evict(program);
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
