package ru.innopolis.stc9.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.dao.teacherSubject.TeacherSubjectDao;
import ru.innopolis.stc9.pojo.TeacherSubject;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherSubjectService implements ITeacherSubjectService {
    private static final Logger logger = Logger.getLogger(TeacherSubjectService.class);
    private static final Logger loggerError = Logger.getLogger(TeacherSubjectService.class);

    @Autowired
    private TeacherSubjectDao teacherDao;

    public TeacherSubjectService(TeacherSubjectDao teacherDao) {

    }

    @Override
    public boolean update(TeacherSubject teacherSubject) {
        logger.info(this.getClass().getName() + " method update started, id = " + teacherSubject.getId());
            teacherDao.update(teacherSubject);
        logger.info(this.getClass().getName() + " method update finished, id = " + teacherSubject.getId());
        return true;
    }

    @Override
    public TeacherSubject getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        TeacherSubject teacherSubject = null;
            teacherSubject = teacherDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return teacherSubject;
    }

    @Override
    public boolean deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
            teacherDao.deleteById(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
        return true;
    }

    @Override
    public boolean add(TeacherSubject teacherSubject) {
        logger.info(this.getClass().getName() + " method add started");
            teacherDao.add(teacherSubject);
        logger.info(this.getClass().getName() + " method add finished");
        return true;
    }

    @Override
    public List<TeacherSubject> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<TeacherSubject> teacherSubjectList = new ArrayList<>();
            teacherSubjectList = teacherDao.getAll();
        logger.info(this.getClass().getName() + " method getAll finished");
        return teacherSubjectList;
    }
}
