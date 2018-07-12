package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Person;
import ru.innopolis.stc9.pojo.hibernate.entities.Team;

import java.util.List;

public interface GroupService {
    /**
     * Список всех групп в системе
     *
     * @return
     */
    List<Team> getAllTeams();

    /**
     * Найти в БД по id
     *
     * @param id
     * @return
     */
    Team getById(long id);

    /**
     * Выборка из БД студентов, которые не зафиксированы ни в одной группе
     *
     * @param group
     * @return
     */
    List<Person> getAllSuitPerson(Team group);

    /**
     * Добавить студента в группу
     *
     * @param groupId
     * @param personId
     */
    void addStudentToTeam(long groupId, long personId);

    /**
     * Удалить студента из группы
     *
     * @param personId
     */
    void delStudentFromTeam(long personId);
}
