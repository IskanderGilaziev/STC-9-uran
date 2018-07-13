package ru.innopolis.stc9.db.hibernate.dao.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Team;

import java.util.List;

public interface TeamDao {
    /**
     * Найти группу по ее id
     *
     * @param id
     * @return
     */
    Team getById(long id);

    /**
     * Удалить группу по id
     *
     * @param id
     */
    void deleteById(long id);

    /**
     * Освежить данные в базе
     *
     * @param team
     */
    void addOrUpdate(Team team);

    /**
     * Список всех групп, присутствующих в системе
     *
     * @return
     */
    List<Team> getAll();

    /**
     * Выборка из БД тех групп, которые еще не окончили обучение
     * (т.е. год начала обучения + срок обучения не превышают текущий год.
     *
     * @param earliestYear самый ранний год, по которой произодится выборка
     * @return
     */
    List<Team> getAllSuitForSpecialty(int earliestYear);
}
