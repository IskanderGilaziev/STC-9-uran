package ru.innopolis.stc9.db.dao.programs;

import ru.innopolis.stc9.pojo.realisationJDBC.Program;

import java.sql.SQLException;
import java.util.List;

public interface ProgramsDao {
    Program getById(long id) throws SQLException;

    Program getBySpeciality(String name) throws SQLException;

    List<Program> getAll() throws SQLException;

    void add(Program program) throws SQLException;

    void update(Program program) throws SQLException;

    void deleteById(long id) throws SQLException;
}
