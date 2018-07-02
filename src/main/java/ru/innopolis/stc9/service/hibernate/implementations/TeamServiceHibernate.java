package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.StudentDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeamDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.pojo.hibernate.entities.Student;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.service.hibernate.interfaces.TeamService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TeamServiceHibernate implements TeamService {
    private static final Logger logger = Logger.getLogger(TeamServiceHibernate.class);
    private final TeamDao teamDao;
    private final PersonDao personDao;
    private final StudentDao studentDao;

    @Autowired
    public TeamServiceHibernate(TeamDao teamDao, PersonDao personDao, StudentDao studentDao) {
        this.teamDao = teamDao;
        this.personDao = personDao;
        this.studentDao = studentDao;
    }

    @Override
    public List<Team> getAll() {
        return teamDao.getAll();
    }

    @Override
    public boolean createNewTeam(String fullName, String shortName, int yStart, int yCurrent, int yLast) {
        boolean result = false;
        if (fullName != null && !fullName.isEmpty() &&
                shortName != null && !shortName.isEmpty() &&
                yStart > 0 && yCurrent > 0 && yLast > 0 &&
                yCurrent >= yStart && yCurrent <= yLast) {
            Team team = new Team(fullName, shortName, yCurrent, yStart, yLast);
            result = teamDao.saveOrUpdate(team);
        } else {
            logger.warn("Invalid form data");
        }
        return result;
    }

    @Override
    public Team getById(long id) {
        Team result = null;
        if (id > 0) {
            result = teamDao.getById(id);
        }
        return result;
    }

    @Override
    public Team getByIdWithStudentsAndSubject(long id) {
        Team result = null;
        if (id > 0) {
            result = teamDao.getByIdWithStudentsAndSubjects(id);
        }
        return result;
    }

    @Override
    public List<Student> getStudentsByTeam(Team team) {
        List<Student> studentsList = new ArrayList<>();
        Set<Student> studentSet = teamDao.getAllStudentInTeam(team);
        studentsList.addAll(studentSet);
        return studentsList;
    }

    @Override
    public int getCountOfSubjectsInTeam(Team team) {
        int result = 0;
        try {
            result = team.getSubjectSet().size();
        } catch (LazyInitializationException e) {
            logger.warn("no one subject");
        }
        return result;
    }

    @Override
    public List<Person> getPotentialStudents() {
        List<Person> studentAll = personDao.getAllPersonsInAllTeams();
        List<Person> personAll = personDao.getPersonByRole(Status.student);
        for (int i = 0; i < studentAll.size(); i++) {
            for (int j = 0; j < personAll.size(); j++) {
                if (personAll.get(j).getId() == studentAll.get(i).getId()) {
                    personAll.remove(j);
                    break;
                }
            }
        }
        return personAll;
    }

    @Override
    public boolean addStudentToTeam(long personId, long teamId) {
        boolean result;
        Person person = personDao.getById(personId);
        Team team = teamDao.getById(teamId);
        Student student = new Student(person, team);
        result = studentDao.addNewStudent(student);
        return result;
    }

    @Override
    public boolean removeStudentFromTeam(long personId, long teamId) {
//        boolean result;
//        Person person = personDao.getById(personId);
//        boolean result = teamDao.removeStudentFromTeam(teamId, personId);
        studentDao.removeStudentFromTeam(teamId, personId);
//        Student student = new Student(person, team);
//        result = studentDao.addNewStudent(student);
        return true;
    }
}
