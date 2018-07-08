package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;

import java.util.List;

public interface SpecialityService {
    List<Speciality> getAllSpecialty();

    /**
     * Создает новую специальность. По умолчанию для создания необходимо указать хотя бы один предмет.
     *
     * @param name
     * @param yTotal
     * @param isActive
     * @param subjectId
     * @return
     */
    boolean createNewSpecialty(String name, int yTotal, boolean isActive, long subjectId);

    Speciality getById(long id);

    boolean updateSpecialty(long specialityId, String newName, int newYTotal);

    boolean delete(long id);

}
