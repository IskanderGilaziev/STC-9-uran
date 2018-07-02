package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface SubjectDao {
    List<Subject> getAllSubjects();

    Subject getById(long id);

    boolean saveOrUpdate(Subject subject);

    long countOfTeachersBySubject(Subject subject);
}
