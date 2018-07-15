package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Performance;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface PerformanceService {
    void addOrUpdateById(Performance performance);

    Performance getById(long id);

    void deleteById(long id);

    List<Performance> getAll();

    List<Performance> getPerfomanceListByPerson(long personId);

    List<Performance> getByLessonId(long lessonId);

    /**
     * Выборка списка предметов, по которым у данного студента были уроки
     *
     * @param person
     * @return
     */
    List<Subject> getListOfSubjectsWithLessonForStudent(Person person);

    /**
     * Выборка академических отметок студента по конкретному предмету
     *
     * @param person
     * @param subject
     * @return
     */
    List<Performance> getListOfPerformanceForStudentBySubject(Person person, Subject subject);
}
