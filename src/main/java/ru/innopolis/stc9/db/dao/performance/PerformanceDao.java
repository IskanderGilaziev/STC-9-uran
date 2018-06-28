package ru.innopolis.stc9.db.dao.performance;

import ru.innopolis.stc9.pojo.realisationJDBC.Performance;

import java.sql.SQLException;
import java.util.List;

public interface PerformanceDao {
    Performance getById(long id) throws SQLException;

    Performance getByStudentId(long id) throws SQLException;

    Performance getByLessonId(long id) throws SQLException;

    List<Performance> getAll() throws SQLException;

    void add(Performance performance) throws SQLException;

    void update(Performance performance) throws SQLException;

    void deleteById(long id) throws SQLException;
}
