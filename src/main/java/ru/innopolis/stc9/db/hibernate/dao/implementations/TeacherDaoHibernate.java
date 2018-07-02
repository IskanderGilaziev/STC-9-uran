package ru.innopolis.stc9.db.hibernate.dao.implementations;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeacherDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Teacher;

@Repository
public class TeacherDaoHibernate implements TeacherDao {
    private static final Logger logger = Logger.getLogger(TeacherDaoHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";
    @Autowired
    private SessionFactory factory;

    @Override
    public boolean addNewTeacher(Teacher teacher) {
        logger.debug(DEBUG_BEFORE);
        boolean result = false;
        if (teacher != null) {
            try (Session session = factory.openSession()) {
                session.beginTransaction();
                session.save(teacher);
                session.getTransaction().commit();
                session.close();
                result = true;
            }
        } else {
            logger.warn(WARN_NPE + "teacher");
        }
        logger.debug(DEBUC_AFTER + result);
        return result;
    }
}
