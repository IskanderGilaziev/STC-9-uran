package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

import java.util.List;

@Repository
public class PersonDaoHibernate implements PersonDao {
    private static final Logger logger = Logger.getLogger(Person.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : person";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public Person getById(long id) {
        logger.debug(DEBUG_BEFORE + id);
        Session session = factory.openSession();
        Person person = session.get(Person.class, id);
        session.close();
        logger.info(logResult(person != null));
        return person;
    }

    @Override
    public List<Person> getAllPersons() {
        logger.debug(DEBUG_BEFORE);
        List<Person> personList = null;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Person");
            personList = query.list();
            session.close();
        }
        logger.info(logResult(!personList.isEmpty()) + personList.size());
        return personList;
    }

    @Override
    public void addOrUpdatePerson(Person person) {
        logger.debug(DEBUG_BEFORE);
        if (person != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(person);
            session.getTransaction().commit();
            session.close();
        } else {
            logger.warn(WARN_NPE);
        }
    }

    @Override
    public void deleteByPersonId(long id) {
        logger.debug(DEBUG_BEFORE);
        if (id != 0) {
            Person person = getById(id);
            Session session = factory.openSession();
            session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();
            session.close();
            logger.info(logResult());
        } else {
            logger.warn(WARN_NPE);
        }
        logger.debug(DEBUC_AFTER);
    }

    @Override
    public List<Person> getPersonByRole(Status status) {
        logger.debug(DEBUG_BEFORE);
        List<Person> personList;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Person where status = :role");
            query.setParameter("role", status);
            personList = query.list();
        }
        logger.info(logResult(!personList.isEmpty()) + personList.size());
        return personList;
    }

    @Override
    public List<Person> getPersonByRoleAndNullUser(Status status) {
        logger.debug(DEBUG_BEFORE);
        List<Person> personList;
        try (Session session = factory.openSession()) {
            Query query = session.createQuery("FROM Person where status = :role");
            query.setParameter("role", status);
            personList = query.list();
        }
        logger.info(logResult(!personList.isEmpty()) + personList.size());
        return personList;
    }

    @Override
    public Person getByName(String name) {
        Person person = null;
        if (name != null && !name.isEmpty()) {
            try (Session session = factory.openSession()) {
                Query query = session.createQuery("FROM Person WHERE name = :param");
                query.setParameter("param", name);
                if (query.list() != null && !query.list().isEmpty()) {
                    person = (Person) query.list().get(0);
                }
            }
        }
        return person;
    }

    @Override
    public void toDetached(Person person) {
        logger.debug(DEBUG_BEFORE);
        if (person != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.evict(person);
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
