package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.PersonDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeamDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.UserDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Status;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.service.hibernate.interfaces.GroupService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;

@Transactional
@Service
public class GroupServiceHibernate implements GroupService {
    private static final Logger logger = Logger.getLogger(GroupServiceHibernate.class);
    private static final String LOG_BEFORE = "First line. Argument(-s): ";
    private final TeamDao teamDao;
    private final PersonDao personDao;
    private final SpecialityDao specialityDao;
    private final UserDao userDao;

    @Autowired
    public GroupServiceHibernate(TeamDao teamDao, PersonDao personDao, SpecialityDao specialityDao, UserDao userDao) {
        this.teamDao = teamDao;
        this.personDao = personDao;
        this.specialityDao = specialityDao;
        this.userDao = userDao;
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
     * Создать новую учебную группу
     *
     * @param nameGroup
     * @param yStart
     * @param specialityId
     */
    @Override
    public void addNewGroup(String nameGroup, int yStart, long specialityId) {
        if (nameGroup != null && !nameGroup.isEmpty() && yStart > 0) {
            Team team = new Team(nameGroup, yStart);
            Speciality speciality = specialityDao.getById(specialityId);
            if (speciality != null && speciality.getIsActive() == 0) {
                team.setSpeciality(speciality);
                teamDao.addOrUpdate(team);
            }
        }
    }

    /**
     * Обновить данные существующей группы
     *
     * @param id
     * @param nameGroup
     * @param yStart
     * @param specialityId
     */
    @Override
    public void updateExitingGroup(long id, String nameGroup, int yStart, long specialityId) {
        if (nameGroup != null && !nameGroup.isEmpty() && yStart > 0) {
            Team team = teamDao.getById(id);
            team.setNameGroup(nameGroup);
            team.setyStart(yStart);
            Speciality speciality = specialityDao.getById(specialityId);
            if (speciality != null && speciality.getIsActive() == 0) {
                team.setSpeciality(speciality);
                teamDao.addOrUpdate(team);
            }
        }
    }

    /**
     * Удалить группу по ее id
     *
     * @param id
     */
    @Override
    public void deleteById(long id) {
        teamDao.deleteById(id);
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
     */
    @Override
    public List<Person> getAllSuitPerson(Team group) {
        logger.debug(LOG_BEFORE + "-");
        List<Person> allStudents = personDao.getAllSuitStudentsForTeam();
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
        Person student = personDao.getById(personId);
        student.setTeam(null);
        personDao.addOrUpdatePerson(student);
    }

    /**
     * Определить учебную группу авторизовавшегося студента
     *
     * @param principal
     * @return
     */
    @Override
    public Team getTeamByUser(Principal principal) {
        Person student = userDao.getByLogin(principal.getName()).getPerson();
        Team team = student.getStatus().equals(Status.student) ? student.getTeam() : null;
        return team;
    }
}
