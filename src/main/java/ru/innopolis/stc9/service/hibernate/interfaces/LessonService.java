package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Lesson;

import java.util.List;

public interface LessonService {
    void addOrUpdateById(Lesson lesson);

    Lesson getById(long id);

    void deleteById(long id);

    List<Lesson> getAll();
}
