package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.hibernate.entities.Teacher;

import java.util.List;

public interface SubjectService {
    List<Subject> getAll();

    void addOrUpdate(Subject subject);

    Subject getById(long id);

    List<Teacher> getAllTechersWithTheSubject(Subject subject);
}
