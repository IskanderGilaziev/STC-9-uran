package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.LessonDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;
import ru.innopolis.stc9.service.hibernate.interfaces.LessonService;

import java.util.List;

@Service
public class LessonServiceHibernate implements LessonService {
    private static final Logger logger = Logger.getLogger(PersonServiceHibernate.class);

    private final LessonDao lessonDao;

    @Autowired
    public LessonServiceHibernate(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    @Override
    public Lesson getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Lesson lesson = null;
        lesson = lessonDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return lesson;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        lessonDao.deleteById(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    @Override
    public void addOrUpdateById(Lesson lesson) {
        logger.info(this.getClass().getName() + " method add started");
        lessonDao.addOrUpdateById(lesson);
        logger.info(this.getClass().getName() + " method add finished");
    }

    @Override
    public List<Lesson> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Lesson> lessonList;
        lessonList = lessonDao.getAll();
        logger.info(this.getClass().getName() + " method getAll finished");
        return lessonList;
    }
}
