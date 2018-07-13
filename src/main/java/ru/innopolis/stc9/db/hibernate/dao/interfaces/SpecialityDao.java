package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;

import java.util.List;

public interface SpecialityDao {
    Speciality getById(long id);

    List<Speciality> getAllSpecialitys();

    /**
     * Выбрать все действующие или архивные специальности
     *
     * @param isActive - индикатор активности
     * @return
     */
    List<Speciality> getAllSpecialitiesByActiveField(int isActive);

    void addOrUpdateSpeciality(Speciality speciality);

    /**
     * Удаляет только специальность, оставляя группы в системе
     *
     * @param id
     */
    void deleteBySpecialityIdFull(long id);
}
