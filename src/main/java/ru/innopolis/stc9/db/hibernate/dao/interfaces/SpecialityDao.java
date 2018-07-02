package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;

import java.util.List;

public interface SpecialityDao {
    Speciality getById(long id);

    List<Speciality> getAllSpecialitys();

    void addOrUpdateSpeciality(Speciality speciality);

    void deleteBySpecialityId(long id);

    Speciality getByName(String name);

    void toDetached(Speciality speciality);
}
