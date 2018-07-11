package ru.innopolis.stc9.service.hibernate.interfaces;

import ru.innopolis.stc9.pojo.hibernate.entities.Team;

import java.util.List;

public interface GroupService {
    /**
     * Список всех групп в системе
     *
     * @return
     */
    List<Team> getAllTeams();
}
