package ru.innopolis.stc9.db.dao.subjects;

import ru.innopolis.stc9.pojo.Subject;

import java.util.List;

public interface SubjectDao {
    Subject getById(long id);

    Subject getByName(String name);

    List<Subject> getAll();

    boolean add(Subject subject);

    boolean update(Subject subject);

    boolean deleteById(long id);
}
