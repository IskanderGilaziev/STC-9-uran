package ru.innopolis.stc9.db.dao.speciality;

import ru.innopolis.stc9.pojo.Speciality;

import java.util.List;

public interface SpecialityDao {
    Speciality getById(long id);

    Speciality getByName(String name);

    List<Speciality> getAll();

    boolean add(Speciality speciality);

    boolean update(Speciality speciality);

    boolean deleteById(long id);
}
