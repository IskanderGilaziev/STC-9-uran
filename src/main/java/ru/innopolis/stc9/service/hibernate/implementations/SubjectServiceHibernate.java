package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.service.hibernate.interfaces.SubjectService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SubjectServiceHibernate implements SubjectService {
    private static final Logger logger = Logger.getLogger(SubjectServiceHibernate.class);

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectServiceHibernate(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public Subject getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Subject subject = null;
        subject = subjectDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return subject;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);

        subjectDao.deleteBySubjectId(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    /**
     * Обновить существующий предмет
     *
     * @param id
     * @param name
     */
    @Override
    public void updateExitingSubject(long id, String name) {
        if (name != null && !name.isEmpty()) {
            Subject subject = subjectDao.getById(id);
            if (subject != null) {
                subject.setName(name);
                subjectDao.addOrUpdateSubject(subject);
            }
        }
    }

    @Override
    public void addOrUpdate(Subject subject) {
        logger.info(this.getClass().getName() + " method add started");
        subjectDao.addOrUpdateSubject(subject);
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Subject> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Subject> subjectList;
        subjectList = subjectDao.getAllSubjects();
        logger.info(this.getClass().getName() + " method getAll finished");
        return subjectList;
    }
}
