package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.hibernate.entities.Teacher;
import ru.innopolis.stc9.service.hibernate.interfaces.SubjectService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class SubjectServiceHibernate implements SubjectService {
    private static final Logger logger = Logger.getLogger(SubjectServiceHibernate.class);
    private static final String DEBUG_BEFORE = "First  line of method. Argument(s): ";
    private static final String WARN_NPE = "Null objest : ";
    private static final String DEBUC_AFTER = "Before exit.";

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectServiceHibernate(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public List<Subject> getAll() {
        logger.debug(DEBUG_BEFORE);
        List<Subject> subjectList = subjectDao.getAllSubjects();
        return subjectList;
    }

    @Override
    public void addOrUpdate(Subject subject) {
        if (subject != null) {
            subjectDao.saveOrUpdate(subject);
        } else {
            logger.warn("NPE");
        }
    }

    @Override
    public Subject getById(long id) {
        Subject result = null;
        if (id > 0) {
            result = subjectDao.getById(id);
        } else {
            logger.warn("negative id");
        }
        return result;
    }

    @Override
    public List<Teacher> getAllTechersWithTheSubject(Subject subject) {
        List<Teacher> teachers = new ArrayList<>();
        try {
            Set<Teacher> teachersWithTheSubject = subject.getTeacherSet();
            Iterator<Teacher> iterator = teachersWithTheSubject.iterator();
            if (iterator.hasNext()) {
                Teacher t = new Teacher(iterator.next().getPerson());
                t.setId(iterator.next().getId());
                teachers.add(t);
            }
        } catch (LazyInitializationException e) {
            logger.info("No one");
        }
        return teachers;
    }
}
