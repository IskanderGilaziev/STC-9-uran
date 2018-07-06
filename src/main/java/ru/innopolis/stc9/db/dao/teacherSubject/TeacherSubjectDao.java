package ru.innopolis.stc9.db.dao.teacherSubject;

import ru.innopolis.stc9.pojo.TeacherSubject;

import java.util.List;

public interface TeacherSubjectDao {
    TeacherSubject getById(long id);

    TeacherSubject getByName(String name);

    List<TeacherSubject> getAll();

    boolean add(TeacherSubject teacherSubject);

    boolean update(TeacherSubject teacherSubject);

    boolean deleteById(long id);
}
