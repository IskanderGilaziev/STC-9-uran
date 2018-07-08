package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface SpecialityDao {
    Speciality getById(long id);

    List<Speciality> getAllSpeciality();

    boolean addOrUpdateSpeciality(Speciality speciality);

    /**
     * Добавить учебную дисциплину к программе
     *
     * @param specialityId
     * @param subject
     * @return
     */
    boolean addNewSubject(long specialityId, Subject subject);

    /**
     * Удалить данныек безвозвратно
     *
     * @param speciality
     * @return
     */
    boolean deleteSpecialityFull(Speciality speciality);

}
