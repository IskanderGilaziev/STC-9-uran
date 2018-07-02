package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface SubjectDao {
    Subject getById(long id);

    List<Subject> getAllSubjects();

    void addOrUpdateSubject(Subject subject);

    void deleteBySubjectId(long id);

    Subject getByName(String name);

    void toDetached(Subject subject);
}
