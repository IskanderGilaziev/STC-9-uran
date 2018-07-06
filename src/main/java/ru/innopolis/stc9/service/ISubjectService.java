package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.Subject;

import java.util.List;

public interface ISubjectService {
    boolean update(Subject subject);

    Subject getById(long id);

    boolean deleteById(long id);

    boolean add(Subject subject);

    List<Subject> getAll();
}
