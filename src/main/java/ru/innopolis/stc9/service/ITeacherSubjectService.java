package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.realisationJDBC.TeacherSubject;

import java.util.List;

public interface ITeacherSubjectService {
    void update(TeacherSubject teacherSubject);

    TeacherSubject getById(long id);

    void deleteById(long id);

    void add(TeacherSubject teacherSubject);

    List<TeacherSubject> getAll();
}
