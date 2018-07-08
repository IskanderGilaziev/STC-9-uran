package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class SubjectService implements ru.innopolis.stc9.service.hibernate.interfaces.SubjectService {
    private static final Logger logger = Logger.getLogger(SubjectService.class);

    private final SubjectDao subjectDao;

    @Autowired
    public SubjectService(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectDao.getAllSubjects();
    }

    @Override
    public boolean addNew(String name) {
        if (name != null && !name.isEmpty()) {
            Subject subject = new Subject(name);
            subjectDao.addOrUpdateSubject(subject);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        if (id > 0) {
            Subject subject = subjectDao.getById(id);
            if (subject != null) {
                result = subjectDao.deleteSubjectFull(subject);
            }
        }
        return result;
    }

    @Override
    public boolean update(long id, String name) {
        boolean result = false;
        if (id > 0) {
            Subject subject = subjectDao.getById(id);
            if (subject != null) {
                subject.setName(name);
                result = subjectDao.addOrUpdateSubject(subject);
            }
        }
        return result;
    }

    @Override
    public Subject getById(long id) {
        Subject subject = null;
        if (id > 0) {
            subject = subjectDao.getById(id);
        }
        return subject;
    }
}
