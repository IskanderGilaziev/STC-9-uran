package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Performance;

import java.util.List;

public interface PerformanceDao {
    void addOrUpdateById(Performance performance);

    Performance getById(long id);

    void deleteById(long id);

    List<Performance> getAll();

    List<Performance> getPerfomanceListByPerson(long personId);

    List<Performance> getByLessonId(long lessonId);
}
