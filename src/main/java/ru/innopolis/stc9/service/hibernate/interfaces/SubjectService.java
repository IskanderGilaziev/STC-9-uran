package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;


public interface SubjectService {
    void addOrUpdateById(Subject subject);
    Subject getById(long id);
    void deleteById(long id);
    List<Subject> getAll();
}
