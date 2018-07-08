package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();

    boolean addNew(String name);

    boolean delete(long id);

    boolean update(long id, String name);

    Subject getById(long id);
}
