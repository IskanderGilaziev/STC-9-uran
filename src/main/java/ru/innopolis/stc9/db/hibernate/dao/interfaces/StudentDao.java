package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Student;

import java.util.List;

public interface StudentDao {
    boolean addNewStudent(Student student);

    List<Student> getAllStudent();

    boolean removeStudentFromTeam(long team, long person);
}
