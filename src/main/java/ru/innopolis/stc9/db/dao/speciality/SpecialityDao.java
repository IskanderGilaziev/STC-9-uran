package ru.innopolis.stc9.db.dao.speciality;

import ru.innopolis.stc9.pojo.realisationJDBC.Speciality;

import java.sql.SQLException;
import java.util.List;

public interface SpecialityDao {
    Speciality getById(long id) throws SQLException;

    Speciality getByName(String name) throws SQLException;

    List<Speciality> getAll() throws SQLException;

    void add(Speciality speciality) throws SQLException;

    void update(Speciality speciality) throws SQLException;

    void deleteById(long id) throws SQLException;
}
