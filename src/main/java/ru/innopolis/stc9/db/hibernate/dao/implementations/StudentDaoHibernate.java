package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.StudentDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDaoHibernate implements StudentDao {
    private static final Logger logger = Logger.getLogger(StudentDaoHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public boolean addNewStudent(Student student) {
        logger.debug(DEBUG_BEFORE);
        boolean result = false;
        if (student != null) {
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            session.close();
            result = true;
        } else {
            logger.warn(WARN_NPE + "student");
        }
        logger.debug(DEBUC_AFTER + result);
        return result;
    }

    @Override
    public List<Student> getAllStudent() {
        logger.debug(DEBUG_BEFORE);
        List<Student> studentList = new ArrayList<>();
        Session session = factory.openSession();
        Query query = session.createQuery("FROM Student");
        studentList = query.list();
        session.close();
        return studentList;
    }

    @Override
    public boolean removeStudentFromTeam(long team, long person) {
        Session session = factory.openSession();
        Query query = session.createQuery("from Student where team.id = :param1 and person.id = :param2");
        query.setParameter("param1", team);
        query.setParameter("param2", person);
        Student student = (Student) query.list().get(0);
//        session.getTransaction();
        session.delete(student);
//        session.getTransaction().commit();
        session.close();
        return true;
    }
}
