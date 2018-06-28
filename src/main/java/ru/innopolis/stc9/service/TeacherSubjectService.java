package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.teacherSubject.TeacherSubjectDao;
import ru.innopolis.stc9.pojo.realisationJDBC.TeacherSubject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherSubjectService implements ITeacherSubjectService {
    private static final Logger logger = Logger.getLogger(TeacherSubjectService.class);
    private static final Logger loggerError = Logger.getLogger(TeacherSubjectService.class);

    @Autowired
    private TeacherSubjectDao teacherDao;

    @Override
    public void update(TeacherSubject teacherSubject) {
        logger.info(this.getClass().getName() + " method update started, id = " + teacherSubject.getId());
        try {
            teacherDao.update(teacherSubject);
        } catch (SQLException e) {
            loggerError.error("Error at method update, id = " + teacherSubject.getId(), e);
        }
        logger.info(this.getClass().getName() + " method update finished, id = " + teacherSubject.getId());
    }

    @Override
    public TeacherSubject getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        TeacherSubject teacherSubject = null;
        try {
            teacherSubject = teacherDao.getById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method getById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return teacherSubject;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        try {
            teacherDao.deleteById(id);
        } catch (SQLException e) {
            loggerError.error("Error at method deleteById, id = " + id, e);
        }
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void add(TeacherSubject teacherSubject) {
        logger.info(this.getClass().getName() + " method add started");
        try {
            teacherDao.add(teacherSubject);
        } catch (SQLException e) {
            loggerError.error("Error at method add", e);
        }
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<TeacherSubject> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<TeacherSubject> teacherSubjectList = new ArrayList<>();
        try {
            teacherSubjectList = teacherDao.getAll();
        } catch (SQLException e) {
            loggerError.error("Error at method getAll", e);
        }
        logger.info(this.getClass().getName() + " method getAll finished");
        return teacherSubjectList;
    }
}
