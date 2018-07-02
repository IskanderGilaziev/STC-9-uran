package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.subjects.SubjectDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService implements ISubjectService {
    private static final Logger logger = Logger.getLogger(SubjectService.class);
    private static final Logger loggerError = Logger.getLogger(SubjectService.class);

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public void update(Subject subject) {
        logger.info(this.getClass().getName() + " method update started, id = " + subject.getId());
        try {
            subjectDao.update(subject);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + subject.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + subject.getId());
    }

    @Override
    public Subject getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Subject subject = null;
        try {
            subject = subjectDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return subject;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            subjectDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(Subject subject) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            subjectDao.add(subject);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Subject> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Subject> subjectList = new ArrayList<>();
        try {
            subjectList = subjectDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return subjectList;
    }
}
