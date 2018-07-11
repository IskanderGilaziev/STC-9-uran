package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.LessonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.service.hibernate.interfaces.LessonService;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

@Transactional
@Service
public class LessonServiceImpl implements LessonService {
    private static final Logger logger = Logger.getLogger(PersonServiceHibernate.class);

    private final LessonDao lessonDao;
    private final PersonDao personDao;

    @Autowired
    public LessonServiceImpl(LessonDao lessonDao, PersonDao personDao) {
        this.lessonDao = lessonDao;
        this.personDao = personDao;
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
    public Lesson add(Subject subject, long teacher_item, String date, String theme, String homework) {
        Person teacher = personDao.getById(teacher_item);
        Lesson lesson = new Lesson(subject, teacher, Date.valueOf(date), theme, homework);
        addOrUpdateById(lesson);
        return lesson;
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

    @Override
    public List<Lesson> getLessonListBySubjId(long subjectId) {
        logger.info(this.getClass().getName() + " method getLessonListBySubjId started");
        return lessonDao.getLessonListBySubjId(subjectId);
    }


}
