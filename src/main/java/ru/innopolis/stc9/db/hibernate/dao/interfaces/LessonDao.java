package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;

import java.util.List;

public interface LessonDao {
    void addOrUpdateById(Lesson lesson);
    Lesson getById(long id);
    void deleteById(long id);
    List<Lesson> getAll();
    List<Lesson> getLessonListBySubjId(long subjectId);
}
