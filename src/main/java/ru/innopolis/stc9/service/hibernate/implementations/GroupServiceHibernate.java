package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeamDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.service.hibernate.interfaces.GroupService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class GroupServiceHibernate implements GroupService {
    private static final Logger logger = Logger.getLogger(GroupServiceHibernate.class);
    private static final String LOG_BEFORE = "First line. Argument(-s): ";
    private final TeamDao teamDao;
    private final PersonDao personDao;

    @Autowired
    public GroupServiceHibernate(TeamDao teamDao, PersonDao personDao) {
        this.teamDao = teamDao;
        this.personDao = personDao;
    }

    /**
     * Список всех групп в системе
     *
     * @return
     */
    @Override
    public List<Team> getAllTeams() {
        logger.debug(LOG_BEFORE + "-");
        List<Team> list = teamDao.getAll();
        logger.info((list != null) ? "found " + list.size() + " group(-s)" : "Fail");
        return list;
    }

    /**
     * Найти в БД по id
     *
     * @param id
     * @return
     */
    @Override
    public Team getById(long id) {
        logger.debug(LOG_BEFORE + " id = " + id);
        Team team = teamDao.getById(id);
        logger.info((team != null) ? team : "Fail");
        return team;
    }

    /**
     * Выборка из БД студентов, которые не зафиксированы ни в одной группе.
     * См. комментарий в методе
     * Список будет пуст, если в группе не задана специальность.
     *
     * @param group
     * @return
     * @link{ru.innopolis.stc9.service.hibernate.implementations.SpecialityServiceHibernate#getSuitGroups(ru.innopolis.stc9.pojo.hibernate.entities.Speciality)}
     */
    @Override
    public List<Person> getAllSuitPerson(Team group) {
        logger.debug(LOG_BEFORE + "-");
        List<Person> allStudents = new ArrayList<>();
        if (group != null && group.getSpeciality() != null) {
            allStudents = personDao.getAllSuitStudentsForTeam();
        }
        logger.info("found " + allStudents.size() + "student(-s)");
        return allStudents;
    }

    /**
     * Добавить студента в группу
     *
     * @param groupId
     * @param personId
     */
    @Override
    public void addStudentToTeam(long groupId, long personId) {
        Person student = personDao.getById(personId);
        if (student != null && student.getStatus().equals(Status.student) && student.getTeam() == null) {
            Team team = teamDao.getById(groupId);
            if (team != null) {
                student.setTeam(team);
                personDao.addOrUpdatePerson(student);
                logger.info("insert operation");
            } else {
                logger.warn("NPE: team with id = " + groupId);
            }
        } else {
            logger.warn("Invalid person with id = " + personId);
        }
    }

    /**
     * Удалить студента из группы
     *
     * @param personId
     */
    @Override
    public void delStudentFromTeam(long personId) {
        personDao.deletePersonFromGroup(personId);
    }
}
