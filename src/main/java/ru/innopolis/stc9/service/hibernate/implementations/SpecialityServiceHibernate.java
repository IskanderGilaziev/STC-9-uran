package ru.innopolis.stc9.service.hibernate.implementations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SpecialityDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.SubjectDao;
import ru.innopolis.stc9.db.hibernate.dao.interfaces.TeamDao;
import ru.innopolis.stc9.pojo.hibernate.entities.Speciality;
import ru.innopolis.stc9.pojo.hibernate.entities.Subject;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;
import ru.innopolis.stc9.service.hibernate.interfaces.SpecialityService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class SpecialityServiceHibernate implements SpecialityService {
    private static final Logger logger = Logger.getLogger(SpecialityServiceHibernate.class);

    private final SpecialityDao specialityDao;
    private final SubjectDao subjectDao;
    private final TeamDao teamDao;

    @Autowired
    public SpecialityServiceHibernate(SpecialityDao specialityDao, SubjectDao subjectDao, TeamDao teamDao) {
        this.specialityDao = specialityDao;
        this.subjectDao = subjectDao;
        this.teamDao = teamDao;
    }

    @Override
    public Speciality getById(long id) {
        logger.info(this.getClass().getName() + " method getById started, id = " + id);
        Speciality speciality = null;
        speciality = specialityDao.getById(id);
        logger.info(this.getClass().getName() + " method getById finished, id = " + id);
        return speciality;
    }

    @Override
    public void deleteById(long id) {
        logger.info(this.getClass().getName() + " method deleteById started, id = " + id);
        specialityDao.deleteBySpecialityIdFull(id);
        logger.info(this.getClass().getName() + " method deleteById finished, id = " + id);
    }

    /**
     * По умолчанию специальность создается активной.
     *
     * @param name
     * @param yTotal
     */
    @Override
    public void addNew(String name, int yTotal) {
        if (name != null && !name.isEmpty() && yTotal > 0) {
            Speciality speciality = new Speciality(name, yTotal);
            specialityDao.addOrUpdateSpeciality(speciality);
            logger.info("after insert operation");
        } else {
            logger.info("invalid parameters");
        }
    }

    /**
     * Обновить существующую специальность, не затрагивая ее статуса действующая/архивная
     *
     * @param id
     * @param name
     * @param yTotal
     */
    @Override
    public void updateExiting(long id, String name, int yTotal) {
        if (name != null && !name.isEmpty() && yTotal > 0) {
            Speciality speciality = specialityDao.getById(id);
            if (speciality != null) {
                speciality.setName(name);
                speciality.setyTotal(yTotal);
                speciality.setIsActive(0);
                specialityDao.addOrUpdateSpeciality(speciality);
                logger.info("after insert operation");
            } else {
                logger.warn("no speciality with id = " + id);
            }
        } else {
            logger.info("invalid parameters");
        }
    }

    @Override
    public List<Speciality> getAll() {
        logger.info(this.getClass().getName() + " method getAll started");
        List<Speciality> specialityList;
        specialityList = specialityDao.getAllSpecialitys();
        logger.info(this.getClass().getName() + " method getAll finished");
        return specialityList;
    }

    /**
     * Выбрать только действующие специальности (без архивных)
     *
     * @return
     */
    @Override
    public List<Speciality> getAllActive() {
        List<Speciality> specialityList = specialityDao.getAllSpecialitiesByActiveField(0);
        logger.info("found " + specialityList.size() + " object(-s)");
        return specialityList;
    }

    /**
     * Подобрать список специальностей для группы при редактировании.
     * Если в группе еще не определены студенты - можно предложить все действующие специальности.
     * Иначе - список ограничивается только
     *
     * @param group
     * @return
     */
    @Override
    public List<Speciality> getSuitSpeciality(Team group) {
        List<Speciality> specialityList = new ArrayList<>();
        if (group.getPersonSet() == null) {
            specialityList = getAllActive();
        } else {
            specialityList.add(group.getSpeciality());
        }
        return specialityList;
    }

    /**
     * Список предметов, которых нет в данной специальности
     *
     * @param speciality
     * @return
     */
    @Override
    public List<Subject> getSuitSubjects(Speciality speciality) {
        /*Я осознаю, что в такой реализации этот метод - зло.
        Правильно было бы сделать хороший запрос в базу.
        Но в hql редакции мне это не поддается, а привязываться к постгрес диалекту - не хочу.
        Это претит идеи использования hibernate.*/
        // TODO: 11.07.2018 прописать правильный запрос к базе
        List<Subject> allSubjects = new ArrayList<>();
        if (speciality.getIsActive() == 0) {
            allSubjects = subjectDao.getAllSubjects();
            for (Subject s : speciality.getSubjectSet()) {
                if (allSubjects.contains(s)) {
                    allSubjects.remove(s);
                }
            }
        }
        return allSubjects;
    }

    /**
     * Список групп, которых не обучаются по данной специальности
     *
     * @param speciality
     * @return
     */
    @Override
    public List<Team> getSuitGroups(Speciality speciality) {
        /*Я осознаю, что в такой реализации этот метод - зло.
        Правильно было бы сделать хороший запрос в базу.
        Но в hql редакции мне это не поддается, а привязываться к постгрес диалекту - не хочу.
        Это претит идеи использования hibernate.*/
        // TODO: 11.07.2018 прописать правильный запрос к базе
        List<Team> allTeams = new ArrayList<>();
        if (speciality.getIsActive() == 0) {
            int earliestYear = LocalDate.now().getYear() - speciality.getyTotal() + 1;
            allTeams = teamDao.getAllSuitForSpecialty(earliestYear);
            for (Team t : speciality.getTeamSet()) {
                if (allTeams.contains(t)) {
                    allTeams.remove(t);
                }
            }
        }
        return allTeams;
    }

    /**
     * Добавить к специальности c указанным id предмет с указанным id
     *
     * @param specialityId
     * @param subjectId
     */
    @Override
    public void addSubject(long specialityId, long subjectId) {
        Speciality speciality = specialityDao.getById(specialityId);
        Subject subject = subjectDao.getById(subjectId);
        if (speciality != null && subject != null) {
            speciality.getSubjectSet().add(subject);
            specialityDao.addOrUpdateSpeciality(speciality);
        }
    }

    /**
     * Уалить из специальности учебную дисциплину
     *
     * @param specialityId
     * @param subjectId
     */
    @Override
    public void delSubjectFromSpeciality(long specialityId, long subjectId) {
        Speciality speciality = specialityDao.getById(specialityId);
        Subject subject = subjectDao.getById(subjectId);
        if (speciality != null && subject != null) {
            Set<Subject> subjectSet = speciality.getSubjectSet();
            if (subjectSet.contains(subject)) {
                subjectSet.remove(subject);
            }
            specialityDao.addOrUpdateSpeciality(speciality);
        }
    }

    /**
     * Добавить к специальности c указанным id учебную группу с указанным id
     *
     * @param specialityId
     * @param teamId
     */
    @Override
    public void addTeam(long specialityId, long teamId) {
        Speciality speciality = specialityDao.getById(specialityId);
        Team team = teamDao.getById(teamId);
        if (speciality != null && team != null) {
            team.setSpeciality(speciality);
            teamDao.addOrUpdate(team);
            /*speciality.getTeamSet().add(team);
            specialityDao.addOrUpdateSpeciality(speciality);*/
        }
    }

    public void delTeamFromSpecialty(long teamId) {
        Team team = teamDao.getById(teamId);
        if (team != null) {
            team.setSpeciality(null);
            teamDao.addOrUpdate(team);
        }
    }

    /**
     * Помещает специальность в статус архива
     * (т.е. по данной специальности можно только доучить уже назначенные группы, но нельзя начать обучать новую группу)
     * или наоборот, делает ее активной.
     *
     * @param speciality id специальности
     */
    @Override
    public void changeStatus(long speciality) {
        Speciality s = specialityDao.getById(speciality);
        s.setIsActive(Math.abs(s.getIsActive() - 1));
        specialityDao.addOrUpdateSpeciality(s);
    }
}
