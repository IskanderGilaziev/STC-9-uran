package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Teacher;

public interface TeacherDao {
    boolean addNewTeacher(Teacher teacher);
}
