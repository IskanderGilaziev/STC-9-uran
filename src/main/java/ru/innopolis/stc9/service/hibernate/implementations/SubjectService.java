package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.service.hibernate.interfaces.ISubjectService;

import java.util.List;

@Service
public class SubjectService implements ISubjectService {
    private static final Logger logger = Logger.getLogger(SubjectService.class);

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectService(SubjectDao subjectDao) {
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
