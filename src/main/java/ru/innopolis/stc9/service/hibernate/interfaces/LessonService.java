package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;

import java.util.List;

public interface LessonService {
    Lesson add(Subject subject, long teacher_item, String date, String theme, String homework);
    void addOrUpdateById(Lesson lesson);
    Lesson getById(long id);
    void deleteById(long id);
    List<Lesson> getAll();
    List<Lesson> getLessonListBySubjId(long subjectId);
}