package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import java.util.List;

public interface SubjectDao {
    Subject getById(long id);
    Subject getByName(String name);
    List<Subject> getAll();
    void addOrUpdateSubject(Subject subject);
    void deleteById(long id);
}
