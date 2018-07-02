package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Student;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;

import java.util.List;

public interface TeamService {
    List<Team> getAll();

    boolean createNewTeam(String fullName, String shortName, int yStart, int yCurrent, int yLast);

    Team getById(long id);

    Team getByIdWithStudentsAndSubject(long id);

    List<Student> getStudentsByTeam(Team team);

    int getCountOfSubjectsInTeam(Team team);

    List<Person> getPotentialStudents();

    boolean addStudentToTeam(long personId, long teamId);

    boolean removeStudentFromTeam(long personId, long teamId);
}
