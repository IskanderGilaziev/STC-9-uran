package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Student;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;

import java.util.List;
import java.util.Set;

public interface TeamDao {
    Team getById(long id);

    Team getByIdWithStudentsAndSubjects(long id);

    List<Team> getAll();

    long getTeamCount();

    boolean saveOrUpdate(Team team);

    Set<Student> getAllStudentInTeam(Team team);

    boolean removeStudentFromTeam(long id, long pers);
}
