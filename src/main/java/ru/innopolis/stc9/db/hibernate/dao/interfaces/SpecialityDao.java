package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;

import java.util.List;

public interface SpecialityDao {
    Speciality getById(long id);

    List<Speciality> getAllSpecialitys();

    void addOrUpdateSpeciality(Speciality speciality);

    /**
     * Удаляет только специальность, оставляя группы в системе
     *
     * @param id
     */
    void deleteBySpecialityIdFull(long id);
}
