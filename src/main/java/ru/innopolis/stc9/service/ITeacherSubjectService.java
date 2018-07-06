package ru.innopolis.stc9.service;

import ru.innopolis.stc9.pojo.TeacherSubject;

import java.util.List;

public interface ITeacherSubjectService {
    boolean update(TeacherSubject teacherSubject);

    TeacherSubject getById(long id);

    boolean deleteById(long id);

    boolean add(TeacherSubject teacherSubject);

    List<TeacherSubject> getAll();
}
