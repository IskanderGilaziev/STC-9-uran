package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;

import javax.transaction.Transactional;

@Transactional
@Service
public class SubjectService implements ru.innopolis.stc9.service.hibernate.interfaces.SubjectService {
    private static final Logger logger = Logger.getLogger(SubjectService.class);

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }


}
