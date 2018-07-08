package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface SubjectDao {
    Subject getById(long id);
    List<Subject> getAllSubjects();

    boolean addOrUpdateSubject(Subject subject);

    boolean deleteSubjectFull(Subject subject);

    /**
     * Добавить уебную дисциплину к учебной программе
     *
     * @param subjectId
     * @param speciality
     * @return
     */
    boolean addNewSpecialty(long subjectId, Speciality speciality);

}
