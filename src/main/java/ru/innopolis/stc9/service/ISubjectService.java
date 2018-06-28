package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.realisationJDBC.Subject;

import java.util.List;

public interface ISubjectService {
    void update(Subject subject);

    Subject getById(long id);

    void deleteById(long id);

    void add(Subject subject);

    List<Subject> getAll();
}
