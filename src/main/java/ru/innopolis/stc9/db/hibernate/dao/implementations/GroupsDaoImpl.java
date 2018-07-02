package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.GroupsDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Group;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class GroupsDaoImpl implements GroupsDao {
    private static final Logger logger = Logger.getLogger(GroupsDaoImpl.class);
    private static final String BEFORE = "First  line of method.";
    private static final String AFTER = "Before exit.";
    public final String ClassName = this.getClass().getName();

//    @Autowired
//    private ProgramsDao programsDao;

    @Autowired
    SessionFactory factory;

//    @Override
//    public List<Group> getAll()  {
//        logger.debug(BEFORE);
//
//        List<Group> result = null;// selectQuery(sql);
//        logger.info(AFTER + "is empty collection? " + (result.isEmpty()));
//        return result;
//    }

    @Override
    public Group getById(long id) {
        logger.info("Class " + ClassName + " method getById started, id = " + id);
        Session session = factory.openSession();
        Group group = session.get(Group.class, id);
        session.close();
        logger.info("Class " + ClassName + " method getById finished, id = " + id);
        return group;
    }

    @Override
    public Group getByProgram(String name) {
        logger.info("Class " + ClassName + " method add started");
        return null;
    }

    @Override
    public List<Group> getAllGroup() {
        logger.info("Class " + ClassName + " method add started");
        Session session = factory.openSession();
        List<Group> result;
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> root = criteriaQuery.from(Group.class);
        criteriaQuery.select(root);
        result = session.createQuery(criteriaQuery).getResultList();
        session.close();
        logger.info("Class " + ClassName + " method getAll finished");
        return result;
    }

    @Override
    public void addGroup(Group group) {
        logger.info("Class " + ClassName + " method add started");
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method add finished");
    }

    @Override
    public void updateGroup(Group group) {
        logger.info("Class " + ClassName + " method update started");
        Session sessionUpdate = factory.openSession();
        sessionUpdate.beginTransaction();
        sessionUpdate.update(group);
        sessionUpdate.getTransaction().commit();
        sessionUpdate.close();
        logger.info("Class " + ClassName + " method update finished");
    }

    @Override
    public void deleteGroupById(long id) {
        logger.info("Class " + ClassName + " method delete started");
        Session session = factory.openSession();
        session.beginTransaction();
        Group group = session.load(Group.class, id);
        session.delete(group);
        session.getTransaction().commit();
        session.close();
        logger.info("Class " + ClassName + " method delete finished, id = " + id);
    }


    @Override
    public List<Group> getListOfNewGroups() {

        return null;
    }

    @Override
    public List<Group> getListOfOldGroups() {

        return null;
    }

    @Override
    public List<Group> getListOfCurrentGroups() {

        return null;
    }
}
