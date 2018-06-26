package ru.innopolis.stc9.db.daoEntity.teacherSubject;

import ru.innopolis.stc9.entity.TeacherSubject;

import java.sql.SQLException;
import java.util.List;

public interface TeacherSubjectDao {
    TeacherSubject getById(long id) throws SQLException;

    TeacherSubject getByName(String name) throws SQLException;

    List<TeacherSubject> getAll() throws SQLException;

    void add(TeacherSubject teacherSubject) throws SQLException;

    void update(TeacherSubject teacherSubject) throws SQLException;

    void deleteById(long id) throws SQLException;
}
