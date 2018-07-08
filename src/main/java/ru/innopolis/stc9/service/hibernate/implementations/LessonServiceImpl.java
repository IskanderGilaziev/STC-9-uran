package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.LessonDao;
import ru.innopolis.stc9.service.hibernate.interfaces.LessonService;

import javax.transaction.Transactional;

@Transactional
@Service
public class LessonServiceImpl implements LessonService {
    private static final Logger logger = Logger.getLogger(PersonServiceHibernate.class);

    private final LessonDao lessonDao;

    @Autowired
    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }


}
