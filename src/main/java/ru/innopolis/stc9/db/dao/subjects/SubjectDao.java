package ru.innopolis.stc9.db.dao.subjects;

import ru.innopolis.stc9.pojo.realisationJDBC.Subject;

import java.sql.SQLException;
import java.util.List;

public interface SubjectDao {
    Subject getById(long id) throws SQLException;

    Subject getByName(String name) throws SQLException;

    List<Subject> getAll() throws SQLException;

    void add(Subject subject) throws SQLException;

    void update(Subject subject) throws SQLException;

    void deleteById(long id) throws SQLException;
}
