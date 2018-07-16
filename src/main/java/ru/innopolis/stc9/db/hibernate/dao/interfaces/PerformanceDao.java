package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Performance;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface PerformanceDao {
    void addOrUpdateById(Performance performance);

    Performance getById(long id);

    void deleteById(long id);

    List<Performance> getAll();

    List<Performance> getPerfomanceListByPerson(long personId);

    List<Performance> getByLessonId(long lessonId);

    List<Subject> getSubjectsForStudent(Person person);

    /**
     * Список оценок студента по конкретному предмету
     *
     * @param person
     * @param subjectId
     * @return
     */
    List<Performance> getPerformanceForStudentBySubject(Person person, Subject subject);
}
