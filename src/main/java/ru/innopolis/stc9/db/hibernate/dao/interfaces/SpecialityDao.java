package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;

import java.util.List;

public interface SpecialityDao {
    Speciality getById(long id);

    List<Speciality> getAllSpecialitys();

    void addOrUpdateSpeciality(Speciality speciality);

    void deleteBySpecialityId(long id);

//    /**
//     * Список дисциплин, которых нет в данной специальности, но есть в базе
//     * @param speciality
//     * @return
//     */
//    List<Subject> getLackingSubjects(Speciality speciality);

}
