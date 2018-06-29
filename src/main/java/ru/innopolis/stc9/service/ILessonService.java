package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.realisationJDBC.Lesson;

import java.util.List;

public interface ILessonService {
    void updateById(Lesson lesson);

    Lesson getById(long id);

    void deleteById(long id);

    void add(Lesson lesson);

    List<Lesson> getAll();
}
