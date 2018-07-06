package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.subjects.SubjectDao;
import ru.innopolis.stc9.pojo.Subject;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService implements ISubjectService {
    private static final Logger logger = Logger.getLogger(SubjectService.class);
    private static final Logger loggerError = Logger.getLogger(SubjectService.class);

    @Autowired
    private SubjectDao subjectDao;

    public SubjectService(SubjectDao SubjectDao) {
    }

    @Override
    public boolean update(Subject subject) {
        logger.info(this.getClass().getName() + " method update started, id = " + subject.getId());
            subjectDao.update(subject);
        logger.info(this.getClass().getName() + " method update finished, id = " + subject.getId());
        return true;
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
    public boolean deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
            subjectDao.deleteById(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
        return true;
    }

    @Override
    public boolean add(Subject subject) {
        logger.info(this.getClass().getName() + " method add started");
            subjectDao.add(subject);
        logger.info(this.getClass().getName() + " method add finished");
        return true;
    }

    @Override
    public List<Subject> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Subject> subjectList = new ArrayList<>();
            subjectList = subjectDao.getAll();
        logger.info(this.getClass().getName() + " method getAll finished");
        return subjectList;
    }
}
